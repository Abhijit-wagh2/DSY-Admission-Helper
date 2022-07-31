package com.aashviit.DSEAssistant.ui.home.cap1;

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

public class Cap1_2021 extends Fragment {
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
        db.collection("Cap12021").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657626605028.pdf?alt=media&token=c8220c64-9f22-46f3-8c80-fdb47985a0fa"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"Sant Gadge Baba Amravati University,Amravati"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633649935.pdf?alt=media&token=4bb0a457-f82e-49f1-bd6d-c1d5105f746a"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633670114.pdf?alt=media&token=38ada6fe-3e97-4487-971c-5bea37f45152"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633710355.pdf?alt=media&token=92ce4015-a381-4601-af62-324f1149cde3"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633739953.pdf?alt=media&token=962a25d1-2284-427c-ad12-3be19cfeb704"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633845642.pdf?alt=media&token=eb25e172-e01a-44af-b9cd-8a32f692b96c"));
        userlist.add(new model("Paramhansa Ramkrishna Maunibaba Shikshan Santha's , Anuradha Engineering College, Chikhali",1119,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633871464.pdf?alt=media&token=6a8ce5ed-1e75-4975-be01-7fb1994f3de0"));
        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633900161.pdf?alt=media&token=5eb4caa1-fe64-41eb-a65d-5babb551e66b"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633948530.pdf?alt=media&token=149604eb-b56e-4833-be26-41da4ae0af60"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657633981974.pdf?alt=media&token=c4ca24d0-ab1d-4b39-b9a5-29cf708c19e2"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634040509.pdf?alt=media&token=84316bce-611c-4eed-b295-115bc04a523d"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634090835.pdf?alt=media&token=5b0f7858-6bdb-4cbc-afe5-f9b4a71c416a"));

        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634150461.pdf?alt=media&token=78a8fd20-c7eb-4ef9-a1ce-5f3ef1a59a45"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634279402.pdf?alt=media&token=b5220e20-bc4b-4b3d-b3f1-9c953347996d"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634317788.pdf?alt=media&token=b830402b-ea29-41bf-941f-d5afa9a5ed12"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634348808.pdf?alt=media&token=086f9b83-7af3-4d94-9ada-b2b5b3fd3c0d"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634370103.pdf?alt=media&token=930b081e-4873-4d16-8a34-0570e6f0748c"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634401691.pdf?alt=media&token=8656cd22-7321-4b9b-a0af-9dee3f4275cd"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634429944.pdf?alt=media&token=6a6029fd-2d3d-4479-a910-016293ac1089"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634481960.pdf?alt=media&token=d7af6c28-8104-46b3-81c1-18a1254258b4"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634581174.pdf?alt=media&token=47e28ed0-b6ea-4525-ad09-7b21fef07e89"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634605981.pdf?alt=media&token=b34c5334-74c2-48b0-ad39-834e2310af66"));

        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634635923.pdf?alt=media&token=3e4e1cd8-e266-4d2c-ad3e-a6382a15dc8a"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634657912.pdf?alt=media&token=6097df59-46cc-49f7-aab7-4d8252e216e4"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634677662.pdf?alt=media&token=aaddc06a-cfb4-4474-ba09-9181c972aa76"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634720560.pdf?alt=media&token=819e43c1-f50b-4272-a410-5486e911c561"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634801737.pdf?alt=media&token=cbaf7aa9-7e0d-4ff6-b37d-bdee8a7f0c99"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"Shri Sai Samajik Vikas Santhas Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad"));
        userlist.add(new model("Adarsh Shikshan Prasarak Mandal's K. T. Patil College of Engineering and Technology, Osmanabad",2146,"Adarsh Shikshan Prasarak Mandals K. T. Patil College of Engineering and Technology, Osmanabad"));
        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634944742.pdf?alt=media&token=93556027-79fa-42fd-b849-d3925f2acf13"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657634967064.pdf?alt=media&token=6fdac008-04c7-4628-97dc-3961373737c2"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635013354.pdf?alt=media&token=5ab2a5b3-7d5a-45f1-b051-9d53ca9be974"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635035806.pdf?alt=media&token=285d1349-6b1e-416a-a807-b248f405d4fc"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635055795.pdf?alt=media&token=1c7d935d-f6b9-4c58-a341-c2d720756193"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635083593.pdf?alt=media&token=6fe914bd-7533-48f7-8956-ffb888c77439"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635113692.pdf?alt=media&token=f6849209-8aec-41a9-918b-be43495950b8"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635152387.pdf?alt=media&token=baeef214-6628-41cd-a55b-453a60d531a5"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635178326.pdf?alt=media&token=c621514d-f7e4-4471-8573-9f83b6413446"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635202763.pdf?alt=media&token=2cadc7fa-4551-47d5-93dd-f01b7bab538c"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635227274.pdf?alt=media&token=11ec6321-120f-4dec-91be-86c6632626e8"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635288520.pdf?alt=media&token=fd66ca63-4b81-4df5-835d-d7e17f78056d"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635317920.pdf?alt=media&token=3321e3ee-9e89-404a-8bf5-e72d4550292b"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657635343199.pdf?alt=media&token=add004ff-cb77-4e5d-b059-f8e232cf19de"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657639602138.pdf?alt=media&token=ca999b82-0130-4b69-b97a-436e59920cd8"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657639636958.pdf?alt=media&token=c0913158-e39b-4281-8c8e-88fb6cf9f292"));

        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657640911857.pdf?alt=media&token=a14d6a8f-2567-42fa-87ad-b91c2e3741bb"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657640940204.pdf?alt=media&token=e9f0cf78-7c04-4933-b7db-1aee0d315637"));

        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657641106972.pdf?alt=media&token=06fa3d40-cb35-4939-b87b-56a5d5753cf4"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642040700.pdf?alt=media&token=210dd64a-402f-435e-a737-a1df88e9737f"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642066437.pdf?alt=media&token=9e32aeb7-a4d2-4781-bde4-38b64043b2d4"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642089236.pdf?alt=media&token=e15d996b-cb82-4f4d-a0d2-269e9c474ad1"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642126984.pdf?alt=media&token=e0e6906e-d3e7-40ac-a87d-6be42d4ecc10"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642153163.pdf?alt=media&token=99ff3392-6a96-460a-b5c0-2216df123a54"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642865916.pdf?alt=media&token=395293c5-d514-4fa8-8493-d6e8b9ca5436"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642895403.pdf?alt=media&token=1966364f-7d5d-44d3-a68c-d2dbed9f7406"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642918372.pdf?alt=media&token=a4e38c71-b1bd-433c-9a84-ce0f99662e84"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657642962195.pdf?alt=media&token=6eca3bdf-2f07-456a-a509-e9c73575f566"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951687352.pdf?alt=media&token=a931988e-5ca8-4f8d-a152-6e694ec030ad"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951726225.pdf?alt=media&token=360c7ed4-7a31-4774-b0f2-64a52116cbaf"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951765818.pdf?alt=media&token=aebfa8ce-9d48-4b47-b0c8-6985d2e2157f"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951801376.pdf?alt=media&token=1c793522-160d-4c70-a038-69e813f39c66"));
        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951836518.pdf?alt=media&token=96937a32-72f4-4e80-bfb6-7fc214b0639c"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951878477.pdf?alt=media&token=4e46d8fa-23a7-4c40-b560-ac565ce9c876"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951878477.pdf?alt=media&token=4e46d8fa-23a7-4c40-b560-ac565ce9c876"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951948780.pdf?alt=media&token=4ac7ef33-282e-444e-ac61-de9d4ed7898a"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951991536.pdf?alt=media&token=65c254d8-ce42-4f9e-9040-f7573b9d6111"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952027333.pdf?alt=media&token=46136c79-b6e3-46b6-9005-04949090852f"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952060291.pdf?alt=media&token=694ace44-4672-4f05-a4e7-3165ef52eb1b"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952098933.pdf?alt=media&token=59c0f98e-7c8b-400a-8c3a-af9890d607be"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952287507.pdf?alt=media&token=62877b9b-3533-4dec-a674-c59c265bf04b"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952329737.pdf?alt=media&token=0a5580c8-2082-4723-aa4f-8babc54e52b9"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952416451.pdf?alt=media&token=b0eeedcf-b126-415d-b092-a14c3361df2c"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952460770.pdf?alt=media&token=6e99a300-161b-44af-817b-3a7259b06f3d"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952602793.pdf?alt=media&token=97d33b81-6bde-48e3-b4cf-99683a3ecbf9"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952659451.pdf?alt=media&token=1505602b-7f9f-448d-b430-b0ae6f02029d"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952709822.pdf?alt=media&token=72ee1fa4-6fda-4178-b62f-fc762b94253b"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering and Management, Nasarapur, Chandai, Karjat",3220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952752414.pdf?alt=media&token=15cb157e-c7b0-4603-a0a1-40f7ceb1f8ee"));
        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044132086.pdf?alt=media&token=d4c0bdd1-afc5-41a1-9223-cd2e15582ef9"));

        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044226116.pdf?alt=media&token=efc93d0f-2c83-4194-8eeb-27ee820e0121"));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044262711.pdf?alt=media&token=8a0eb44c-e7c9-4191-a37c-5ab47f5616a9"));
        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044302772.pdf?alt=media&token=b9e9ed95-e788-4e8f-b406-d3c254dbb3bb"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044340210.pdf?alt=media&token=0127077d-060c-4bc3-ae97-55b867a89da1"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044380768.pdf?alt=media&token=a87fc1ce-a1c3-47d3-b2c0-cacb543d777e"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044412654.pdf?alt=media&token=35fa21e0-0b48-432e-8b99-cf680443076a"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044485722.pdf?alt=media&token=5967da52-7434-4223-8f27-88adcf14be7f"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044520584.pdf?alt=media&token=4412d22d-c4ed-4166-a15a-d5f93c73655e"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044560193.pdf?alt=media&token=e867c0bd-ef22-415b-8f37-97eaa0deaf85"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044560193.pdf?alt=media&token=e867c0bd-ef22-415b-8f37-97eaa0deaf85"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044641319.pdf?alt=media&token=09af7e74-0db6-4635-8e6f-fa9bf679c1d2"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044680164.pdf?alt=media&token=db7c6b54-93bb-454c-b595-05cb215d9231"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044720060.pdf?alt=media&token=e54012fe-2913-4583-8770-b4f1000c404d"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044757508.pdf?alt=media&token=b9b986cc-5581-4cc2-aa6c-6473b06e1f91"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044793501.pdf?alt=media&token=dd52c1d6-c74b-466b-968f-25080621d56e"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044828248.pdf?alt=media&token=4b620b01-80b6-412c-b4ee-8cd53262a53d"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044864608.pdf?alt=media&token=9717a1cb-4dbc-4e3b-ab98-10e31ee153d6"));
        userlist.add(new model("Indala College Of Engineering, Bapsai Tal.Kalyan",3503,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044894550.pdf?alt=media&token=17c0edd8-907d-4a39-95cf-da471d43642e"));
        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044930766.pdf?alt=media&token=e2b849a4-f79b-4bfd-a6f4-2c12fbe14203"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044965160.pdf?alt=media&token=46ce23dd-c390-4812-b5f4-cad9c8e7c387"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045000518.pdf?alt=media&token=113451a9-1b24-42f1-a8a3-fe406de21434"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045102544.pdf?alt=media&token=35b88f79-3e5b-47a8-86dc-b8bfac7ef23e"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045135445.pdf?alt=media&token=170c6b29-3cbd-4fa7-944c-8b4ae70b6d13"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045176003.pdf?alt=media&token=aa867154-0bb9-47a0-978a-45d91abcef69"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045211832.pdf?alt=media&token=76355741-9efe-417f-8a36-48abb1ed14c7"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045249050.pdf?alt=media&token=ce2ffd4f-997c-4704-8850-3256eed78a07"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045292273.pdf?alt=media&token=5250e9df-217e-4035-8a0f-740b3110d4a6"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045334272.pdf?alt=media&token=ad1041ec-d0c8-42d9-80d6-2535ab83de26"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045372304.pdf?alt=media&token=80d97167-9b6e-4688-b280-2882783d9165"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045409308.pdf?alt=media&token=84e2622d-1aa6-48f1-8c61-09d858035f4c"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045446699.pdf?alt=media&token=94350001-7584-48df-b2d0-95d752598dc8"));

        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045586860.pdf?alt=media&token=7a949fab-74e7-4003-99a7-90cc57744f96"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4441,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045621112.pdf?alt=media&token=a94cacd8-8cfb-414d-a77a-4409737101c4"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045656909.pdf?alt=media&token=bde99f8c-b856-4fb3-848d-758155525075"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045693912.pdf?alt=media&token=314260d8-7af4-47eb-9684-54cdce188f79"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045739204.pdf?alt=media&token=cf14f15e-cf4c-4e80-8db6-6c0e0149d827"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045772042.pdf?alt=media&token=b764b6a3-ae51-45a8-b73d-29ceaa863907"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045805709.pdf?alt=media&token=1e7cd41e-e07e-4001-b5ef-881672de43dd"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045843408.pdf?alt=media&token=9dda4eb9-b1dc-4be6-9af2-07e99927b556"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045878423.pdf?alt=media&token=b645ff01-d837-4b35-a852-abb056bc1829"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045918195.pdf?alt=media&token=0bf03d9b-56a9-4a6e-8ccc-9ac693a583da"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045953991.pdf?alt=media&token=2881a3f8-60c2-4677-ab2e-d021ae277c19"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046002312.pdf?alt=media&token=b11bf363-e951-49c5-9090-0646f3772d95"));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046049194.pdf?alt=media&token=172e079c-a41a-4db8-a5b5-d856d4e6d13b"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046084656.pdf?alt=media&token=d87ff22b-6538-4d12-9b07-8f8dc1279806"));
        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046218167.pdf?alt=media&token=b271ac60-ca12-42d9-804f-aa39fc5f8b9b"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046273393.pdf?alt=media&token=a7ca49de-904f-4d8c-aab5-9898813721b7"));
        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046310244.pdf?alt=media&token=32772bf4-3295-4f5e-b0b7-7642f425c15f"));

        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046376145.pdf?alt=media&token=951f3a9d-90c2-43ba-8178-b050b6afba5a"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046410869.pdf?alt=media&token=dc0859c4-5155-4d28-a257-e8b29c33d3a6"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046446255.pdf?alt=media&token=d580b646-62aa-4f05-ad66-434b190e3023"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046496935.pdf?alt=media&token=8a2ed43f-c27f-4a4c-a9e2-cd3c00805174"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046532061.pdf?alt=media&token=7196c1a3-f604-4877-963c-b58dad358b83"));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046565633.pdf?alt=media&token=6ce09bf8-1137-44dd-aba0-b46fbd065dbd"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046607545.pdf?alt=media&token=82545fab-f27e-45db-9770-dd93a8f9194a"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046734038.pdf?alt=media&token=2f681600-1c0e-4a53-808f-0d17a3887f79"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046770802.pdf?alt=media&token=c3d2e98d-d8f9-462b-b8f1-4d4639fec11d"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046870879.pdf?alt=media&token=e29c606e-a986-4266-8aa7-3576174d4c72"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048615443.pdf?alt=media&token=fd46cc64-f399-40ca-926f-1d56ae462a4f"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048665494.pdf?alt=media&token=6886b99d-3b06-4b41-ae4e-1c10f7aaeef6"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048707198.pdf?alt=media&token=e3e1ea37-dc04-4c31-a55e-2b3c437d3b3e"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048749496.pdf?alt=media&token=ce05266c-6f8c-460b-9b11-c99374bb38e8"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048792642.pdf?alt=media&token=f3fa1fb5-8aef-422d-824f-edaef08e4930"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050236168.pdf?alt=media&token=34e5be9b-8007-4681-9d33-9fd61439d587"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050271109.pdf?alt=media&token=a0932fbe-4e9f-4020-825f-3c0eadadb19e"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050313372.pdf?alt=media&token=56779671-136b-4c33-92c2-7bfcb9287730"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050351291.pdf?alt=media&token=8d58b3ef-d6b9-4ade-b911-b571c72d1d0a"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050456481.pdf?alt=media&token=30683b4b-60c8-4ca1-bf19-f71e98f94771"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050417172.pdf?alt=media&token=08051d90-d881-455a-a104-7f8e2fb9cdaa"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050497805.pdf?alt=media&token=ab497b36-fb9b-4c51-920f-d596698497cd"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050533266.pdf?alt=media&token=ba8dc0d0-6dde-4d91-8faa-6db26ee11cca"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050575031.pdf?alt=media&token=350be4ef-ecf8-4f77-8277-ff2d1e9abd1b"));
        userlist.add(new model("G. H. Raisoni Institute of Business Management,Jalgaon",5152,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050614289.pdf?alt=media&token=08fe5c9f-64b0-4179-851a-3de9e9861bbc"));
        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658051008418.pdf?alt=media&token=d06b72bd-0124-4db5-8b51-6683c808b414"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134860978.pdf?alt=media&token=4dda8114-ceb1-410a-885e-48d87f26cbf9"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134902416.pdf?alt=media&token=1fb2fa13-7e06-4901-9cf3-21c8a46ab9c0"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134941348.pdf?alt=media&token=c212b590-b351-4bc8-b042-5ff0e8c195d0"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135029153.pdf?alt=media&token=a63ae07f-5e95-4a43-b515-e288da0f5690"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135074097.pdf?alt=media&token=b00022dd-944e-4512-b244-725bf8e12703"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135105926.pdf?alt=media&token=388dd7cc-6eea-4209-a6b7-151a5aba047d"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135152346.pdf?alt=media&token=d60e0dec-efe4-42a8-89fa-0449f45dd0f0"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135251405.pdf?alt=media&token=24a9e42d-d584-45ee-9206-28ae8d9afb01"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135213665.pdf?alt=media&token=dd5fb722-892f-4c29-a02b-937d1d208492"));

        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135332182.pdf?alt=media&token=9123553d-914f-482f-937b-083ee24363c6"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135374355.pdf?alt=media&token=1908e3c0-d530-4a92-9252-9e0b6c5f26fd"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135407842.pdf?alt=media&token=16be2146-7b17-461f-8b07-56f90fed1751"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135442692.pdf?alt=media&token=5492562c-00d7-4886-a2c4-9644f45ded38"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135474069.pdf?alt=media&token=3c66ca95-bf3e-452c-b8a5-f85fba72641a"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135512788.pdf?alt=media&token=683747ac-6a5d-4e67-b0ae-756cafdf1b11"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135560906.pdf?alt=media&token=b03a1831-414e-444a-b3d7-f61002e647d3"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135600791.pdf?alt=media&token=70d9a040-234d-47b9-b8cb-7b873aa5c3f3"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135685116.pdf?alt=media&token=4ec68b56-e142-4987-904d-696c129deabe"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135643601.pdf?alt=media&token=21fff72b-1679-44a5-9c58-43a026d156ab"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135720504.pdf?alt=media&token=f32bfda8-610e-46c7-939b-35d2de8013b1"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136049476.pdf?alt=media&token=ba09e2c3-3a5a-4f59-84fc-ca1c81a183b0"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136089449.pdf?alt=media&token=faeb40d5-e406-4da3-bfe1-b9a60e939992"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136125348.pdf?alt=media&token=4d0d8d09-729a-4ced-b5b5-8f4cb9df4b9b"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136159538.pdf?alt=media&token=a86b208a-5731-4d6a-bc1d-7b6d144812b5"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136204157.pdf?alt=media&token=113071f1-7c6e-47c7-9a09-0abc9325b903"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136247985.pdf?alt=media&token=33f4e1d4-4fdf-4fb7-b087-96239f2c722b"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136280237.pdf?alt=media&token=7a22ce29-23e6-4b8a-9263-5cd4a6010447"));

        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136344426.pdf?alt=media&token=5cde534b-a3d0-46cc-aa17-2db93f1f950f"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136379902.pdf?alt=media&token=641f835d-bf1d-40e0-833d-234c2950c979"));
        userlist.add(new model("Government College of Engineering karad",6005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136434784.pdf?alt=media&token=87b3df19-f51a-424e-8ac0-2c21b68555c5"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136473653.pdf?alt=media&token=aaf87fb7-7190-4aa9-aedd-776782bbf75b"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136510354.pdf?alt=media&token=a404666f-a677-4f3b-aba2-987b77065810"));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136544375.pdf?alt=media&token=2492fda4-9cf9-4ae0-bd75-6241961d4882"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136576509.pdf?alt=media&token=bf1609f4-6918-4fcd-910c-1f81f61c1594"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136634334.pdf?alt=media&token=3e413255-ef1b-45dd-afcd-8d14d6dfb5f8"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136666333.pdf?alt=media&token=ff60dd3d-14b5-42df-ae1a-b2fce0ad8462"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136700484.pdf?alt=media&token=7d96eb43-3f3c-435a-ade4-22b8723fc667"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136740226.pdf?alt=media&token=dce7d9a1-8121-4e79-bc40-74d31dbc5856"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136775301.pdf?alt=media&token=1efa2986-f6e9-49bf-99b9-ba448c0d53a8"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136815663.pdf?alt=media&token=42ab80b8-84ed-4cbe-bcf9-c4c753106762"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136884967.pdf?alt=media&token=18c2abe4-80ba-4957-9983-a7e153bd19a3"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136921156.pdf?alt=media&token=0e855a02-5666-439d-9ac2-f578342a1bca"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136951282.pdf?alt=media&token=46efbeb9-9193-497f-a886-d64b6eee7e70"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136993852.pdf?alt=media&token=e0c2c02f-d47f-42bd-9d59-62b032a99450"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137027195.pdf?alt=media&token=9cda0098-3217-480a-96cb-81921abbcf96"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137079275.pdf?alt=media&token=38c37bd6-24d6-4e55-a2c9-6d1faea4ce59"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137114593.pdf?alt=media&token=ae71f5f2-adbe-44f3-b8ae-c0e1bbfc5443"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137158824.pdf?alt=media&token=02bbfadb-d57b-4ac7-add0-1dfedaf407ff"));
        userlist.add(new model("Indira College of Engineering & Management, Pune",6179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137191040.pdf?alt=media&token=19b073e2-ac8c-4205-a0d6-cccb16f251a4"));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137227146.pdf?alt=media&token=a388069c-3179-4177-9f53-f9801f36e755"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137263412.pdf?alt=media&token=f1a11c12-b0c3-4b60-866f-274379cdfe69"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137300323.pdf?alt=media&token=1a5df195-f879-489b-a9ae-49e6e1663ad9"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137337193.pdf?alt=media&token=1eb9e189-33e8-4fbb-8d4b-7be0cd2ca884"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137370509.pdf?alt=media&token=cc859654-8b46-4868-a451-474ec81cbdd7"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143420467.pdf?alt=media&token=447098a7-4894-412f-a483-58d93a47b9b2"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143458606.pdf?alt=media&token=e9fb508d-d573-4698-b90f-56091f8246bb"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143491711.pdf?alt=media&token=7ff36e9e-95ca-4189-adf3-ae13bad3226f"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143538392.pdf?alt=media&token=4af827c0-122d-44fb-8602-6af0a1b74c26"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143575893.pdf?alt=media&token=ad8387f8-5733-4666-ad39-8ee039e8e28c"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143618221.pdf?alt=media&token=ef3fc3bc-d6c2-4f3d-b5e0-223e3999f671"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143654149.pdf?alt=media&token=4d113632-9d95-4e0d-b1c3-87f8847a9492"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143687291.pdf?alt=media&token=247cab59-e4e9-4236-9c93-8d548961900c"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143736767.pdf?alt=media&token=9d261786-b045-4dba-b992-181e74e5b881"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143774684.pdf?alt=media&token=e512493d-1f18-4f35-a8cb-bf8891a2b08e"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143813951.pdf?alt=media&token=de167ca7-d383-4217-9daf-5d3e70d64423"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143867206.pdf?alt=media&token=2a98d076-a0df-4d34-a6d0-bb62eb521579"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143912393.pdf?alt=media&token=c5d317d5-eeb8-4d0b-a4f2-092fe806b055"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143974941.pdf?alt=media&token=b573171c-e56b-4c14-a6b3-f36047dd16c9"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144014023.pdf?alt=media&token=56744594-29a4-4bff-ae2d-966a091bb9aa"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144048517.pdf?alt=media&token=62a2a5d0-0b62-4dd3-95eb-8dff2b60fcdd"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144139132.pdf?alt=media&token=c1f0a8ae-2c05-4a5f-9936-dd15f9187d05"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144218305.pdf?alt=media&token=ab07f6fd-32e5-4d43-a849-cb7b8c85feed"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144251911.pdf?alt=media&token=bc778d93-ad65-442f-96e4-515a9ebfc307"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144287476.pdf?alt=media&token=caf70c53-e314-4428-9c66-ac6602fcc68b"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144324780.pdf?alt=media&token=3cd131f7-7bc8-4812-8241-d14f36d53375"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144362308.pdf?alt=media&token=3f3c12b9-1222-48cb-9eed-ad048aaec05b"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144403768.pdf?alt=media&token=dee1df7a-159b-4cf1-bd44-7f21ceec2705"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144439195.pdf?alt=media&token=b2f80b52-3a20-4c12-9efd-7ae922377cae"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144472291.pdf?alt=media&token=0a9c7334-25fa-42e7-a817-fa5f21c68db6"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144516739.pdf?alt=media&token=4a6a25bc-84a7-49ea-a8ad-23c173d1e07f"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144551268.pdf?alt=media&token=a472b63b-4505-4b41-8762-0e5bc019753b"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144588936.pdf?alt=media&token=0ade1932-ed38-4031-a1d1-ac5e170b5ea5"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144636228.pdf?alt=media&token=db3523be-e585-49ab-8046-e24c87186afb"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144678019.pdf?alt=media&token=d9ff4fca-fb0c-4580-887b-36786ae02b56"));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144716767.pdf?alt=media&token=3098e6a6-70bb-4ef2-9986-3166b3e2dcc0"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144750780.pdf?alt=media&token=e05c9378-1eec-4eba-99e3-0a7cf2e8f5e5"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144796072.pdf?alt=media&token=eb71fbb3-89ee-4f06-b9dd-509de8b9985f"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144833516.pdf?alt=media&token=2e2939b0-0a3e-4b21-aa8d-d541944c7b22"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144865435.pdf?alt=media&token=8e3dded6-a356-4758-b2a2-77035896bacd"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144900271.pdf?alt=media&token=aa5d3767-3795-40ee-9259-c7b5e307a199"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144946620.pdf?alt=media&token=73da84c9-baeb-4958-895b-e6f81d6a14b2"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144979809.pdf?alt=media&token=6c70f292-15f3-4e90-9294-f20353759038"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145012111.pdf?alt=media&token=7b4ff5cb-cf21-4d76-9829-81ceb902a75c"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145046901.pdf?alt=media&token=d7f13335-617e-4ea7-9481-5c6eaa1d11a4"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145081548.pdf?alt=media&token=1370c390-75e9-452c-8ef1-e7edd44ef7c3"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145121247.pdf?alt=media&token=45975f08-ede8-433f-95be-01fcb5a0fce8"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145153279.pdf?alt=media&token=7c978c16-2b5b-45c8-824e-0065f29a61c3"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145189200.pdf?alt=media&token=eade0410-bc38-4ed7-847f-bc4f3ef9f4ac"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145230478.pdf?alt=media&token=f2dcc179-aa00-4b96-815d-cdb33b014f34"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145265097.pdf?alt=media&token=2eeabc3f-c596-4e1a-9881-e6971cbbf6a7"));

        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145325959.pdf?alt=media&token=9a919c5e-4d5d-4062-9fbf-af4a234ee570"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145356819.pdf?alt=media&token=ca0b4b5a-63f5-47ca-ac6e-92deeec149ea"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145391700.pdf?alt=media&token=cb24ec8d-6e30-4aff-a37c-569dd3a468a8"));
        userlist.add(new model("Nutan College of Engineering and Research, Talegaon Dabhade Tal. Maval, Pune",6419,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156394562.pdf?alt=media&token=7228d78d-c6dd-4fb9-9518-ec547c2a0ec4"));
        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156431911.pdf?alt=media&token=f65219a3-824e-4adf-afe1-3badfc1d2864"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156470441.pdf?alt=media&token=45a4ed32-9bce-471b-acaa-26d507947c12"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156510797.pdf?alt=media&token=2030ca0a-51b7-4251-a579-3cdcc42cb329"));

        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156574349.pdf?alt=media&token=620ef7a5-bcbe-4a98-8df7-30eef182dc5d"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156609691.pdf?alt=media&token=12f3e34e-a5e1-4b1a-841a-809b9cf08d6d"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156642380.pdf?alt=media&token=391efe29-3b86-4dc4-9c4f-321534ba58b2"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156753275.pdf?alt=media&token=78eb55f7-f92e-4794-8721-257cdcc88f44"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156785511.pdf?alt=media&token=c8c094f3-0d0d-4c6e-bd78-982323ba1262"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156822034.pdf?alt=media&token=1ef2e7cd-8193-415e-be6a-00df2743d794"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156859157.pdf?alt=media&token=0d85f1fc-713d-47f2-af3d-3610d02b2c0d"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156891335.pdf?alt=media&token=23872ba7-d2db-4a11-9541-d9de7ff5af01"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156925498.pdf?alt=media&token=328f6e99-822d-46b6-9833-dd17ad029371"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156963672.pdf?alt=media&token=ea2c52a0-3619-46ab-98cb-a5812be4cec6"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157114502.pdf?alt=media&token=e987453c-2a36-49b2-829d-3e4e12822275"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157150389.pdf?alt=media&token=3a6622f5-db11-4dee-b1c4-0e81fde70fc5"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157182979.pdf?alt=media&token=e71243a7-79e8-4e11-b5cc-55f7926a6098"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157217298.pdf?alt=media&token=407a56dc-cc64-4f18-9d2d-16f6d9d520eb"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157248827.pdf?alt=media&token=34a8e85b-ff58-43b4-b915-4c86deac66a8"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157284131.pdf?alt=media&token=4d62bb3f-3f9b-4cd4-a790-a8a184d299a1"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157319539.pdf?alt=media&token=6bf33e07-07c9-4f8f-9c0a-b4126609c6d4"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157361553.pdf?alt=media&token=b1ea2360-3d92-43c3-982c-170aa7462343"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157392255.pdf?alt=media&token=6e2f26d2-bbc4-4d46-90a9-be213930aae8"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157426015.pdf?alt=media&token=9ce93413-5162-4ff5-8295-4e3fd4de0490"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157461513.pdf?alt=media&token=6ccfbb4e-9b5f-4376-b1e8-fb9f366fd64c"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157496359.pdf?alt=media&token=d7bb1e9e-d106-4acf-abe0-676bef377352"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157532910.pdf?alt=media&token=b9f5e675-5cfb-4a86-912f-15d3a4e707b7"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157579557.pdf?alt=media&token=8b554760-5a89-4868-a65d-e7a3cf9de7f9"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157620283.pdf?alt=media&token=d1e80345-58b0-4b12-aab1-260125202c56"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157650176.pdf?alt=media&token=bc5fc4aa-501d-481e-80da-8eff7906a1fe"));

        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157712062.pdf?alt=media&token=acf8c6ef-7f89-4d2c-9a6e-8e7d9949bebc"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157744356.pdf?alt=media&token=fc609ba6-2c46-4fed-b5e8-34945fb842e5"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157778075.pdf?alt=media&token=066d6062-bfa6-48de-ad00-e64be3b19389"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157810168.pdf?alt=media&token=4266fbfb-e982-48bf-9feb-90fba9de58db"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157853189.pdf?alt=media&token=dcc3238b-9c1b-442f-b503-dd51f0a642c5"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157897561.pdf?alt=media&token=12af91f3-4e43-4931-a5e2-5d8a2938ac49"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157930638.pdf?alt=media&token=a50002e0-9dd6-415e-85d0-153437661b18"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157962520.pdf?alt=media&token=cf90100e-da64-4ca9-91b8-5c3f5762e64d"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157997561.pdf?alt=media&token=33680d24-7483-4770-aa31-c5632cd50be4"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158034132.pdf?alt=media&token=c4d312fd-e012-4148-bac8-463636d41305"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6869,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158068441.pdf?alt=media&token=2c50de2e-3b4b-4982-adaa-eaaf474da6cf"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158103457.pdf?alt=media&token=8138b482-da02-45d1-9a53-f7d82effb70c"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158139824.pdf?alt=media&token=15b8d37d-4ad2-4e32-8a6b-ae2b8b5195c9"));
        userlist.add(new model("Shree Siddheshwar Women's College Of Engineering Solapur.",6938,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158179505.pdf?alt=media&token=951c9d25-c17b-4989-9873-afa551ce1a55"));
        progressDialog.dismiss();
    }





}
