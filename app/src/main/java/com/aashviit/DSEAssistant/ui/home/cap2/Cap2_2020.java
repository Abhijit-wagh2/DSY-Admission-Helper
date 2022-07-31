package com.aashviit.DSEAssistant.ui.home.cap2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aashviit.DSEAssistant.R;
import com.aashviit.DSEAssistant.ui.home.model;
import com.aashviit.DSEAssistant.ui.home.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Cap2_2020 extends Fragment {
    RecyclerView recview;
    View view;
    myadapter adapter;
    FirebaseRecyclerOptions<model>  options;
    ArrayList<model> userlist;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    EditText editText;
    ImageView refresh;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.cap_1_2018, container, false);
        recview = (RecyclerView)view.findViewById (R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progressDialog= new ProgressDialog(view.getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fething Data...");
        progressDialog.show();
        editText=(EditText) view.findViewById(R.id.editTextTextPersonName);
        refresh=(ImageView)view.findViewById(R.id.refresh);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=FirebaseFirestore.getInstance();
                userlist=new ArrayList<model>();
                initdata();
                adapter=new myadapter(view.getContext(),userlist);
                recview.setAdapter(adapter);
                //EventChangeListener();
            }
        });


        db=FirebaseFirestore.getInstance();
        userlist=new ArrayList<model>();
        initdata();
        adapter=new myadapter(view.getContext(),userlist);
        recview.setAdapter(adapter);
        //EventChangeListener();
        return view;
    }






    private void filter(String text){
        ArrayList<model> filterlist=new ArrayList<>();
        for(model item:userlist){
            if(item.getCollageName().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(item);
            }
        }
        adapter.filterlist(filterlist);
    }



    private void EventChangeListener() {
        db.collection("Cap22020").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    if(progressDialog.isShowing()){
                      progressDialog.dismiss();
                    }
                    Log.e("Firestore Error",error.getMessage());
                    return;
                }

                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED);
                    userlist.add(dc.getDocument().toObject(model.class));
                }
                adapter.notifyDataSetChanged();
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }



    private void initdata() {
        userlist=new ArrayList<model>();
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785513422.pdf?alt=media&token=d3d78119-8f26-4b51-a980-43170f177a44"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785562031.pdf?alt=media&token=cda206cd-75a9-48d2-a2cc-48340b95ebb3"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785633191.pdf?alt=media&token=fad81648-92d3-440e-9a20-78e0061bf865"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785696791.pdf?alt=media&token=d06d636e-836a-4db1-9ad6-8be20c366893"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785827906.pdf?alt=media&token=b5998cc2-8722-4007-b6e9-44a73fb1d8cf"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785897526.pdf?alt=media&token=64620d0d-9e12-4b78-ba37-7a60e3aad325"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785967184.pdf?alt=media&token=e9325067-3983-4bdc-9cc9-68bf6a1417b5"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786037768.pdf?alt=media&token=7eafebb7-2ada-4fae-881b-8e64d4e7499b"));
        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786113607.pdf?alt=media&token=159ba996-c452-4399-8bb2-41645938629d"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786187458.pdf?alt=media&token=6201daa8-c717-406f-aeaf-1d66c3b6bbff"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786253806.pdf?alt=media&token=3c1247d4-c3b7-4c86-9aea-83193baf5089"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786478775.pdf?alt=media&token=ad2211d0-b542-41da-ba6a-55a8fc3415b0"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786636712.pdf?alt=media&token=78ea8e4b-2380-44e2-931a-07be6f622cbf"));

        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786836911.pdf?alt=media&token=97d0e8df-8ff3-4612-950c-1ed4c9934ed0"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787247501.pdf?alt=media&token=2c530083-3c9a-4cad-b0d9-2742707ecb01"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787313287.pdf?alt=media&token=fdf9643a-8134-4bbd-a42a-dd6ff8f68a5f"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787383376.pdf?alt=media&token=f63f96db-4c53-4b7e-a593-050165ddcfe2"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787449054.pdf?alt=media&token=156b06cf-ebf8-4e07-b208-fb4f2b4e331c"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787515147.pdf?alt=media&token=d6ca7c5b-0c5d-4cab-96a5-c3a04e0275a8"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787584180.pdf?alt=media&token=f821223e-3469-437f-8b99-eae5354f1f20"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787664899.pdf?alt=media&token=e8815b4d-d834-49d2-a038-8ba95e05a012"));
        userlist.add(new model("Gramodyogik Shikshan Mandal's Marathwada Institute of Technology, Aurangabadd",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787799646.pdf?alt=media&token=125dc9e8-c7e2-4c60-a527-566d3875ba54"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787862466.pdf?alt=media&token=b84bcf29-b3e3-4f85-8d1c-84087892c0c7"));
        userlist.add(new model("Maharashtra College of Engineering, Nilanga",2128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787937005.pdf?alt=media&token=9851be15-f6a0-4785-ad31-aacf7ac03b36"));
        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787996477.pdf?alt=media&token=edd0db86-cc8a-4757-aa6a-eb092d3c958c"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788061506.pdf?alt=media&token=f4227e7e-1221-415a-bfff-41dea12d0eef"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788129668.pdf?alt=media&token=e3a722e3-26da-4ad8-8c59-e65c59bdc00a"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788206370.pdf?alt=media&token=0d6d68b8-ec28-4aea-8e70-f4485c38fcfe"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788322526.pdf?alt=media&token=2922157a-0739-4780-a7d9-4df7c9ba087e"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788397295.pdf?alt=media&token=0909fc58-9fb4-4763-bb32-1c004af18fb9"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad"));

        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788785997.pdf?alt=media&token=f5a2ecab-50cc-4d12-aa9c-b1e459bbfd05"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788924582.pdf?alt=media&token=9ddb45b9-9e8c-41a0-9ed7-6e5a0de16f0f"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788994045.pdf?alt=media&token=7b8f1d44-3475-4eef-9ae5-100112e1b829"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789153466.pdf?alt=media&token=4c2cb324-7968-41c6-8050-bc66862a5031"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789216098.pdf?alt=media&token=10a4bd00-a87a-40cb-9b5f-4eeb86fbcbe0"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789310659.pdf?alt=media&token=43530fde-103b-4c2f-bf76-b1413443cf24"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795657650.pdf?alt=media&token=a49d3229-0dfd-4b3f-b3ad-0439dc622fb2"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795740151.pdf?alt=media&token=3f43b43b-eb44-4c4c-81d8-821529c3868f"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795800391.pdf?alt=media&token=727d6b3f-83ac-4fc2-b1c0-88651e4fdc0c"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795871106.pdf?alt=media&token=18a057d5-4f8a-4ac6-9e85-0855bbdb444e"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795931220.pdf?alt=media&token=2634dabf-61a9-4471-8231-7bdc2974764b"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796001545.pdf?alt=media&token=ed24e1b8-4f1b-4b45-9ef8-80e231813444"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796071712.pdf?alt=media&token=ecd48fc4-3cf9-4a78-b975-a331cd1e2f62"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796135846.pdf?alt=media&token=5bad8269-63b5-43ba-84c4-f53df669327f"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796244744.pdf?alt=media&token=3fa4840b-7bbe-40fb-aa34-7072560ab05a"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796327266.pdf?alt=media&token=18403cfa-b30f-45f5-86ad-ea15efd857d4"));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796405043.pdf?alt=media&token=530f3e92-df77-47c7-ab22-faea55158dd0"));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796459668.pdf?alt=media&token=3c06d662-8d78-43ad-9aa1-24f34c74cd9e"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796537382.pdf?alt=media&token=d69af019-2be3-4ebc-b6b0-b6a711ce327b"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796609100.pdf?alt=media&token=77f425ac-bf74-4e69-a400-ac53b33b2bb0"));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796663310.pdf?alt=media&token=edf688a1-a9bf-4b46-85de-24202e4ab5dc"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796730540.pdf?alt=media&token=3bf190f8-6e46-4a25-b73c-bddb1bd972fd"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796815190.pdf?alt=media&token=cf2433a3-30bd-4102-88c0-bc87588a867d"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796880252.pdf?alt=media&token=3884a80d-f028-428c-b004-3e283843d9cb"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796962180.pdf?alt=media&token=f1c5ee77-4977-49c4-a1d7-df25fcb9f376"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797034222.pdf?alt=media&token=d30b55ea-2739-4ff3-8019-b61c6dcbf499"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797112299.pdf?alt=media&token=c1af6284-03d4-4959-bc82-d4c8ee06b7df"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797178722.pdf?alt=media&token=d731dd03-11b2-4aac-ad92-ef4155dd8ffd"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797266477.pdf?alt=media&token=39b7b296-6d24-4aee-8f86-65b0684007a9"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797330531.pdf?alt=media&token=8ac1cd0d-9392-4257-a33b-c54051ef977e"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797393650.pdf?alt=media&token=6fa864cb-9f10-4224-ac95-6436e8fb722d"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797472261.pdf?alt=media&token=ab17faa6-88bc-4aa7-9fc3-8827df668796"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797531482.pdf?alt=media&token=dd24e187-0895-4aa8-8b9d-eb7499f8d74b"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797594715.pdf?alt=media&token=0ea9458f-c578-48c0-93a4-ceb1081838f9"));
        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797655980.pdf?alt=media&token=ef2cb661-0d39-4a2b-85bf-45c48ca100f1"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797782690.pdf?alt=media&token=f5c10648-8d99-43e1-b845-69f32997012a"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797844690.pdf?alt=media&token=79e68414-b63d-4347-949d-bfbc5929addc"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797902144.pdf?alt=media&token=199fde4f-e658-471a-b30b-501d48ad82c7"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797962028.pdf?alt=media&token=b0a0bc3a-8ae4-4975-950d-05f1f13a84ce"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798021986.pdf?alt=media&token=c3bf1374-e0a8-4aed-9a06-f83c0a7a7455"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798086941.pdf?alt=media&token=a3180196-d516-47a9-90e6-55dbd717d291"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798209017.pdf?alt=media&token=84a846ba-02e0-4b3b-bd27-098f8a8c0759"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798271282.pdf?alt=media&token=8f124933-7293-420e-9789-a7fc27b9f82c"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798334041.pdf?alt=media&token=f3aa94a2-3de1-4391-bcca-7b8c9233af4e"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798395047.pdf?alt=media&token=b1b293c0-5d3a-4d1d-a251-bdb23155c518"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799120931.pdf?alt=media&token=c291d519-42cb-4a55-a227-acaae511a298"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799288917.pdf?alt=media&token=2e218cc9-0c91-46a5-ba4e-36c3b9ef809b"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799358244.pdf?alt=media&token=f95f1701-d414-4a2e-b7e2-0ba7f5a0d0a1"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799437976.pdf?alt=media&token=3d7a2a53-027d-4031-bf4d-cde8fe51c523"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering and Management, Nasarapur, Chandai, Karjat",3220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799498111.pdf?alt=media&token=8fcb1646-1692-44cb-a570-cf76a14532c9"));
        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799570529.pdf?alt=media&token=67c045f9-46f9-4f3f-8d99-7156c796a244"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799692699.pdf?alt=media&token=7984e7df-038e-47c5-abe9-80dd728acdce"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799775560.pdf?alt=media&token=179cd28d-72ae-4d15-9d10-f9fe5e7e5b9d"));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799832234.pdf?alt=media&token=f85e4214-8cdf-44ef-b6b3-4b72d2a37ae6"));
        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657800066564.pdf?alt=media&token=091b4bc0-ee81-443e-ab32-9aa31a1a97a1"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803492039.pdf?alt=media&token=1c7d9ad3-c879-4622-8182-436e0ae6c5e0"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803556227.pdf?alt=media&token=9709f2e1-848d-49d1-bf0c-bd800e20e1c7"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803617707.pdf?alt=media&token=755f9667-488c-4636-bf35-5ff8b36461e3"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803681720.pdf?alt=media&token=d2707186-dc6a-4e3b-8399-1e3610249d33"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803740949.pdf?alt=media&token=7cb73611-e74a-4c49-bc69-16c0e7a064c5"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803808376.pdf?alt=media&token=091f794a-9888-43e8-8760-ab0161d57a81"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803869903.pdf?alt=media&token=d55eb079-2130-47ec-930b-3c04effd170b"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803921265.pdf?alt=media&token=aeb03d3b-53ab-44c1-a003-31d421f75b8b"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803977177.pdf?alt=media&token=9843b4f4-e4e1-4faf-9878-2b54fc6754a8"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804037551.pdf?alt=media&token=457f1fc0-a0fb-4f5d-b732-787172048d7e"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804108138.pdf?alt=media&token=b0e8cb49-f387-4e75-956a-e13ef1da7f82"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804180344.pdf?alt=media&token=ef542813-8f26-4b95-8269-0afb3cf0d652"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804249678.pdf?alt=media&token=ee21a40e-8165-4e2b-9f58-d4ee3f91391e"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804308465.pdf?alt=media&token=7746bf25-0ed1-4e43-9193-2e34cf044cf3"));

        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804373081.pdf?alt=media&token=7af756c9-9535-4087-8e86-8cf93f74b1c9"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804438211.pdf?alt=media&token=2c617b9c-d310-4c78-a85d-3fb49bbc454c"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804506975.pdf?alt=media&token=ef155340-c631-4eb2-a160-4ca297a3f1e4"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804583427.pdf?alt=media&token=6dd3beaf-b32b-49cf-acc5-28abd946dc8f"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804655681.pdf?alt=media&token=b88962af-1800-46cb-9530-399f2361cc0e"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804722486.pdf?alt=media&token=046a6a8d-aa44-4f48-aa9b-be83b449eb4b"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804806138.pdf?alt=media&token=81d6fdf2-520c-42a1-955c-7c10f13fc8e6"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804875433.pdf?alt=media&token=2d453fde-ccda-4807-a238-da864a8a9446"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804980470.pdf?alt=media&token=9a4577c2-38c4-4da2-9135-c8865e7606ad"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805051714.pdf?alt=media&token=c1ca5114-a01d-4730-8c45-af913169a2f1"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805115996.pdf?alt=media&token=0d8d1658-0f0e-47db-a0bc-42aad823888f"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805194903.pdf?alt=media&token=bcf7c4c5-7d5e-42cd-9293-79e533116cb1"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805252330.pdf?alt=media&token=61b0c797-b596-4207-8e29-5980be1fb00e"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805306804.pdf?alt=media&token=e2934a0b-fc1a-4017-8e63-2684f9fc3ae1"));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805365013.pdf?alt=media&token=a232d430-04c5-459e-a94f-b13b71a87191"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805437725.pdf?alt=media&token=ad96deb3-7329-4420-b996-a501014af40a"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805500799.pdf?alt=media&token=7a2698d0-5962-46b8-9123-926e4672e993"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805559835.pdf?alt=media&token=0781e410-a5ef-4d84-9f5a-a1284b42b406"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805628996.pdf?alt=media&token=ff5a2d5b-ab88-412a-9d7c-ae0d785b1405"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805688506.pdf?alt=media&token=5fde71d7-0d93-4b07-a924-dab7291cb853"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805779206.pdf?alt=media&token=513d2132-30e8-424c-a277-78b1c3944ef2"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865761413.pdf?alt=media&token=ddd6ef67-5ea5-4dbd-8125-d6496af381b1"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865829006.pdf?alt=media&token=1228b471-affb-4c4a-baa4-066bcd8d7fc7"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865916707.pdf?alt=media&token=995fd653-fb49-421e-81c5-ca248350de13"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865977553.pdf?alt=media&token=1d16aebb-e908-4025-94ab-3f15b6a73cb6"));

        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866140857.pdf?alt=media&token=e216f4c7-6735-4c7a-8d5f-ae7928cc88ec"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866213362.pdf?alt=media&token=7e4d3ab3-72cf-4dee-aad8-9480d1288513"));

        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870851524.pdf?alt=media&token=730e1454-07f1-4cf1-9adc-50121ee8e975"));
        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870938371.pdf?alt=media&token=093010e7-ee4a-46a1-835d-6344edbec61a"));
        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871014349.pdf?alt=media&token=8e142586-189a-494f-8118-7d66b480896c"));
        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871181913.pdf?alt=media&token=ec3650b6-186d-4c19-be43-cddc82cffd07"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871242005.pdf?alt=media&token=7196d055-af48-4a02-8fa5-49dec4a4c566"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871304171.pdf?alt=media&token=49accee5-bfd3-49b6-8d3c-42c977708bdc"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871371868.pdf?alt=media&token=9506e6df-5557-4e97-ba0b-e33d17b2e977"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871435083.pdf?alt=media&token=6f356f4c-b583-4184-9d53-95f8e642910c"));

        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871553279.pdf?alt=media&token=88d5b154-2de9-437e-ac2d-15cb37ecb5f4"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871632182.pdf?alt=media&token=24331cf7-55d5-4b59-8646-d7263f8202e3"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871727125.pdf?alt=media&token=979194af-fc71-4858-a379-97574e229bb4"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871799525.pdf?alt=media&token=0947b934-7a41-48b1-85f8-5701a8d6c25a"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871855840.pdf?alt=media&token=bf14ec53-8b1d-4598-8eee-b6d6e40f6206"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872023171.pdf?alt=media&token=56044729-1b99-4c27-a3e0-046f1ef711b0"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872098649.pdf?alt=media&token=c1b628a8-50ba-48d1-a407-c5998d78fef5"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872163040.pdf?alt=media&token=f64e9da2-be0d-49c3-80db-22f168463013"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872226828.pdf?alt=media&token=fc6f03f7-daf1-4646-9eca-55d3c73eeeea"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872355998.pdf?alt=media&token=ab7b59f8-5f16-438e-86ee-cf7715eb7c79"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872553707.pdf?alt=media&token=835ff039-f612-4c92-888d-08dc4e0e6b71"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872506667.pdf?alt=media&token=f02c6e42-acae-4a2f-9426-29410bde8ebf"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872611477.pdf?alt=media&token=fbef7fe8-573c-4e95-b925-5d3e7ad32de8"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872672210.pdf?alt=media&token=32335362-5a04-45d6-8939-4a37e3356020"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872728120.pdf?alt=media&token=c553e58e-767b-4e41-a45a-5ac3d902431c"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872823880.pdf?alt=media&token=e95fe13c-afe8-4b36-9dde-6561129a7fa4"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872920000.pdf?alt=media&token=43572afa-ee9b-4985-9b26-79d44ecf8548"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872979945.pdf?alt=media&token=0f51e5b0-86fd-4a24-85ac-ace7040b90d2"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873046534.pdf?alt=media&token=6092de43-cca8-458c-afcf-6a481cbdb953"));

        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873103589.pdf?alt=media&token=266ef134-d0b2-45c5-b653-4e374ccfb1a4"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873158931.pdf?alt=media&token=d3b72fec-df63-446b-9fe9-8a76a667b250"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873215477.pdf?alt=media&token=c4c3259f-3cf1-4a98-8403-e255df1c97c3"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873269005.pdf?alt=media&token=bbf9f7e3-6209-4129-ad9e-36471d1e2a3f"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873334989.pdf?alt=media&token=1f5cb2fc-00f5-490c-ac15-c6e033d1e8a0"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873389489.pdf?alt=media&token=44493cee-224c-4c81-8013-243581b0a993"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873451424.pdf?alt=media&token=6451c2de-d9d8-49f6-a2c3-f0547c19de4d"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873513172.pdf?alt=media&token=a698b417-2ffb-4d67-b90a-5da7dd08279f"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873581400.pdf?alt=media&token=7045e4f1-c06d-45f5-bd50-18277c3761cb"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873653031.pdf?alt=media&token=e33c7f98-838c-4876-804a-b6a0410d7ec8"));
        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873713027.pdf?alt=media&token=31994ac0-d488-418e-9f8f-2c02ee99b819"));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873754488.pdf?alt=media&token=a55aad48-4446-4f8d-b64d-337c016dbbde"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873815849.pdf?alt=media&token=6dd7a0b3-408b-4333-a389-624416dd5d06"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873877734.pdf?alt=media&token=48f32c50-881b-4fbd-800b-39f64c93f591"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873939101.pdf?alt=media&token=30e09abf-d1aa-4f55-a2a9-011a2a894815"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880036221.pdf?alt=media&token=b7ceed2b-4261-438f-872a-4f6f075954e4"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880105106.pdf?alt=media&token=da724159-b5d7-4120-9c74-cafb5ee11886"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880175243.pdf?alt=media&token=299f7972-5e35-4cc8-aafe-341503381e2f"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880235228.pdf?alt=media&token=593f1afa-660b-4071-b528-ffcb8ad1556b"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880355137.pdf?alt=media&token=1520cfdc-851d-4d91-afba-d1f56398fafc"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880291537.pdf?alt=media&token=5a7747b5-6af1-4ce4-92d0-168286d691b3"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880418355.pdf?alt=media&token=56f6407b-f333-4c63-a24d-4aa40326261f"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880471875.pdf?alt=media&token=fc0ee7c8-820b-417f-8589-297553d638df"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880528571.pdf?alt=media&token=f0b5edf0-b664-44ad-ac87-7a9c724a3b1b"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880580980.pdf?alt=media&token=d4758010-193f-4eb7-89aa-c9f8b365d729"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657881733882.pdf?alt=media&token=aa71ff69-c90d-4618-88be-0189b87fb76c"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657882851883.pdf?alt=media&token=318f300a-e449-4906-9661-102d97f342d5"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657882995276.pdf?alt=media&token=cdc2ab78-d3dd-40e8-9aa7-1ab1e6922328"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883058438.pdf?alt=media&token=707b080e-156c-42fd-9a4d-1b928e996859"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883185201.pdf?alt=media&token=34927806-ee75-48af-b6f6-b5d2afd4ac6e"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883261548.pdf?alt=media&token=04815972-cfee-46bc-be04-acdbd7a05cd1"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883345694.pdf?alt=media&token=3a54a085-321a-4f99-933d-14fb4c067430"));
        userlist.add(new model("Government College of Engineering & Research,karad",6005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883403815.pdf?alt=media&token=bf2d77f4-3787-454a-ba34-d3a8d70bc593"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883461838.pdf?alt=media&token=cddd3361-01e7-4489-bff3-f6edc0016489"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883522868.pdf?alt=media&token=c80ac5d6-672d-427e-8707-6ec8886b2f88"));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883580739.pdf?alt=media&token=02aba8ab-b7ce-45b7-8d02-544c6d6f3f9d"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883642822.pdf?alt=media&token=281ecbf6-c830-40e6-a143-35f70f6af8c4"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883703472.pdf?alt=media&token=2e4d425f-dfe8-4ad2-b8a5-a7f95143502c"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883769755.pdf?alt=media&token=4fc5aad1-58d1-458a-a41c-ba6b529f195e"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884028256.pdf?alt=media&token=c83b35d6-7c70-4f38-b426-91128a0575d6"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884097093.pdf?alt=media&token=fedf59a4-d4d4-441d-85bf-c7aef16c1697"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884162970.pdf?alt=media&token=09fcf3d4-4de5-447d-b79f-26fefecc175a"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884228174.pdf?alt=media&token=196c130e-9f96-4a72-a03c-3adefd08bd4f"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884284563.pdf?alt=media&token=026fdb63-0959-4b0e-8922-9a43754d5057"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884343745.pdf?alt=media&token=6200b41e-24c5-4ac2-8da3-eb516e696e1a"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884446601.pdf?alt=media&token=8dd9181a-f602-43b4-8394-781f6c9cced2"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884504043.pdf?alt=media&token=613425a3-0011-42ed-913b-dc6cdd587095"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884588099.pdf?alt=media&token=ba5538d5-fb45-45d7-b0a7-3eab992cc6c6"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884653436.pdf?alt=media&token=c8cd0a70-31ae-47df-bf52-4fc062fb5705"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884709229.pdf?alt=media&token=f07d737b-d77c-4349-a55e-acbf1ab1b3df"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884777781.pdf?alt=media&token=6013615d-8ce0-4020-9a49-f5a04dee6e0b"));

        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884864395.pdf?alt=media&token=13ef61a9-9e13-4f1d-9573-a1072136877c"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884929909.pdf?alt=media&token=edb03f1b-ea37-4f3a-b6ba-75b3873263a4"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884996094.pdf?alt=media&token=f80b33b2-b8ea-4cf5-9fa8-bf30a7bc077a"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885063729.pdf?alt=media&token=59f1fb7c-2780-47a6-bf23-66d253ed2ffd"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885127941.pdf?alt=media&token=5b0c3e38-170d-4d77-af12-24f2ae0b3518"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885187929.pdf?alt=media&token=9f0dd475-bd62-46ff-b130-62523dc42a8a"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885249779.pdf?alt=media&token=cd3d8afb-12d3-4b51-a934-c121d7df8437"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885306682.pdf?alt=media&token=eb2f5c3a-16a4-4bf4-afd1-55468c9b73dc"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885370611.pdf?alt=media&token=f7c3e75a-bcc1-41ad-81b7-5df0e22dc13a"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885455093.pdf?alt=media&token=8cba94e8-fa4f-48d5-90c1-571a0e8665cb"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885509614.pdf?alt=media&token=37193803-18e0-441f-9d6d-8c6b87e3b330"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885566529.pdf?alt=media&token=6a673118-0520-4dcf-ab95-9051fa8b1841"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885638144.pdf?alt=media&token=9c94aa6a-b3ea-4a53-b6e4-6b42e818f7e3"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885708026.pdf?alt=media&token=918a2282-58e2-40b1-a055-2deddcf26f68"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885772679.pdf?alt=media&token=a36f0d26-d291-4dbf-be81-d30db28203b0"));

        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885882863.pdf?alt=media&token=0a0e6e68-78f8-440f-9240-5144d83e7f1f"));

        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886077986.pdf?alt=media&token=1528f286-4f96-4728-a57e-9c65b4fab7cd"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886144714.pdf?alt=media&token=2267a199-2067-4865-8432-d57f8b764fb3"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886205934.pdf?alt=media&token=078dc100-0fae-4a1a-abba-768eed19a9b0"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886277738.pdf?alt=media&token=61c4b323-8786-43a6-9bc7-4105075d7582"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886349501.pdf?alt=media&token=be48df48-d958-43d1-a768-c09c9c399c8a"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886486286.pdf?alt=media&token=69904200-c152-472f-afb4-11d7751a2f41"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886553040.pdf?alt=media&token=a64ae531-b024-469f-ab48-3e5e010792ae"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886716173.pdf?alt=media&token=0a1772d6-ec88-4ed2-b0b8-8c397a0cfda0"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886793387.pdf?alt=media&token=581e081a-0cc6-4260-9f26-36373b120bde"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896861585.pdf?alt=media&token=b4e301c6-a8e4-417f-aa48-9795d19aae54"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896961544.pdf?alt=media&token=56c0f516-55aa-4ae6-967a-16a4040bd479"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897057166.pdf?alt=media&token=7bbb85a3-f238-45c0-958a-3ec414f0055b"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897262728.pdf?alt=media&token=b48413ab-21e8-4a5a-9356-28ac389a8af4"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897342562.pdf?alt=media&token=874a6263-b944-4879-9838-9a0afb70633a"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897705173.pdf?alt=media&token=015a8ede-f4cd-4e53-9688-2a6701e4aedd"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897771163.pdf?alt=media&token=79696cfe-87a7-40e1-98fa-a97a51460efd"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897879520.pdf?alt=media&token=d86d971b-45d0-471b-beb5-056fa0fe13d3"));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898587974.pdf?alt=media&token=f2bc85be-187b-4c19-98ef-e8cd8874265e"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898765339.pdf?alt=media&token=b3a9a23e-7a5c-4c1f-8378-513e4030f288"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898860506.pdf?alt=media&token=4e9e020e-bcd9-4f42-91eb-083bf6d9b6f7"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898944818.pdf?alt=media&token=ec0ee519-0db9-43a1-8080-02ad36cfb032"));

        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899079183.pdf?alt=media&token=b6fa10d6-3925-42ae-a463-8be21b6f82b7"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899169059.pdf?alt=media&token=131c6b96-8ba3-457d-8a5c-e8b2874cdb59"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899242935.pdf?alt=media&token=43190662-ef93-4d21-8e07-323870f626bf"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899331813.pdf?alt=media&token=5ce4d425-96b8-458f-9a41-203fdf7f127a"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899403182.pdf?alt=media&token=a2efc973-24a9-4855-907a-1818d4d46bd1"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899485423.pdf?alt=media&token=19cc55cb-6a84-4c6e-934c-6a120f76cffb"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899567840.pdf?alt=media&token=dfbdcad4-a963-405e-a87b-ed171d87b75c"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899640197.pdf?alt=media&token=e89ed7b2-f35d-4d36-aaef-72f086d45668"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899724078.pdf?alt=media&token=eb9fc3e9-c136-49f2-854d-7d07c7a4fe68"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899810817.pdf?alt=media&token=15ca81cc-df9f-4ecc-bb57-4f49ff4ffa2b"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899900690.pdf?alt=media&token=2ad4bc6d-23b7-4c06-82d0-d7a927513e90"));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901017153.pdf?alt=media&token=1aeb4b4b-477c-4ade-af42-acc7364c156f"));

        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901183628.pdf?alt=media&token=5e36b76c-42e0-4875-901b-3a718f707f99"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901263355.pdf?alt=media&token=16df5a7a-3d39-471e-b84e-91e5cdcfcf28"));

        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901338929.pdf?alt=media&token=cd516032-5d73-4e18-80d8-6354cff1ef30"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901416697.pdf?alt=media&token=2702e023-e85f-4d81-9170-a4e4060dff58"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901490893.pdf?alt=media&token=fb9c9be0-ef77-4496-856a-3b634cbdbf56"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901599259.pdf?alt=media&token=0a16c467-6e7e-4656-9715-87a7170acd4f"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901707967.pdf?alt=media&token=f6a25685-7844-4d33-8a91-dc8d2f2617b0"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901791972.pdf?alt=media&token=7af78f1a-9e67-4455-9c7a-75fa7fed349d"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901906556.pdf?alt=media&token=cf23bfd0-a486-4f67-ae1a-085a6a194751"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657902163440.pdf?alt=media&token=ee7be566-b83f-46ee-b489-c6fd56ff736b"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906793616.pdf?alt=media&token=1904bb2b-a0b3-4b43-a762-e401ce806a4a"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906876600.pdf?alt=media&token=3a06d334-ecf5-4ad2-bd11-e3f292208f23"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906949678.pdf?alt=media&token=ee282ec4-c088-4048-8f91-dd8159f0190e"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907015915.pdf?alt=media&token=0af22f6b-cafb-40d3-bcab-5a86a9145ac8"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907080055.pdf?alt=media&token=f0676254-0143-4613-911d-5a5e0c180061"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907130708.pdf?alt=media&token=0f65069f-c1c8-4f22-bab8-19d74b38e927"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907208357.pdf?alt=media&token=ac937afb-3c9d-46c6-a4d6-35f31a0baf7b"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907280547.pdf?alt=media&token=9c6cc577-f141-4f53-9bf9-2978e34f50d5"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907348975.pdf?alt=media&token=26a86522-0b9d-4a57-88f6-ab5a5b100733"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907414097.pdf?alt=media&token=717df145-d820-4a04-943e-665c1b71a024"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907470718.pdf?alt=media&token=9004801d-f71f-4751-b4d8-87c4c76611c3"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907537063.pdf?alt=media&token=6f34caf4-455b-4fb3-84f1-642068f0a590"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907598572.pdf?alt=media&token=cedc8c30-b405-44ff-9143-28feda04a823"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907652748.pdf?alt=media&token=6b115099-49f5-440e-8579-36c5d1c2e822"));

        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907807396.pdf?alt=media&token=83e8e508-2c3d-4732-8d09-911f4b2c1394"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907873168.pdf?alt=media&token=dcd3c4d9-d488-415b-b4e9-55a2bd64a56c"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907933134.pdf?alt=media&token=705639e4-c2ab-44ed-93d7-82b1cb3e59eb"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908025162.pdf?alt=media&token=006c9669-5fb0-490e-bc52-7575a6ffa060"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908084552.pdf?alt=media&token=ff4baa71-03d8-4452-b4b4-fffd3c685bf9"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908145213.pdf?alt=media&token=9d2927d1-ba1f-40e3-aa53-db14d5ece7a7"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908203684.pdf?alt=media&token=1da7d36b-1aaf-4ab7-b168-a86ea8df0552"));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908295948.pdf?alt=media&token=294bc7b0-5f59-45de-8c09-630085711b53"));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908346410.pdf?alt=media&token=be0714f0-3e7c-4df5-915d-4e91e2c537af"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908408927.pdf?alt=media&token=648a2d61-14d1-4e66-bd4a-081c6476f022"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908470040.pdf?alt=media&token=8c6d0697-2876-421e-991f-a1f5c54791d0"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908529151.pdf?alt=media&token=bc365977-451f-4a42-bca1-f7aedaa34d8c"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908588104.pdf?alt=media&token=cfd7f770-5450-4d28-8f78-f5c9b9aa5af6"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908655557.pdf?alt=media&token=69edfb4c-08d1-474a-9431-8a8d2962de4e"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908716240.pdf?alt=media&token=9a03e767-2fe8-4650-a44f-802af3849348"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908806798.pdf?alt=media&token=dff6278e-6efc-48e4-88f6-b8d97cc285b8"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908884467.pdf?alt=media&token=0129d27b-e107-41a7-ad21-9d1099fc17d7"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908934993.pdf?alt=media&token=3140e22d-1603-4417-af95-a6b1f75c8651"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6839,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909004681.pdf?alt=media&token=98eb58f6-1402-4c40-9cb7-66fe53b770ee"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909052351.pdf?alt=media&token=3eeb3463-b24c-4c22-a4a4-81f8333da995"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909112439.pdf?alt=media&token=a4a4b697-58b2-4be2-b7c0-4eae9c52b4f2"));

        progressDialog.dismiss();
    }




}
