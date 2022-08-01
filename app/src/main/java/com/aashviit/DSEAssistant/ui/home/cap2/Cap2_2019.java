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

public class Cap2_2019 extends Fragment {
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
              //  EventChangeListener();
            }
        });


        db=FirebaseFirestore.getInstance();
        userlist=new ArrayList<model>();
        initdata();
        adapter=new myadapter(view.getContext(),userlist);
        recview.setAdapter(adapter);
      //  EventChangeListener();
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
        db.collection("Cap22019").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785487070.pdf?alt=media&token=de90d649-b827-4786-aca2-ebd803b5087e"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785590425.pdf?alt=media&token=6d03ce7b-ee4d-4127-9ce2-a49d9990f5e9"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785658101.pdf?alt=media&token=c8a1d41e-ebf6-476b-947d-8f0afa0474d6"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785721637.pdf?alt=media&token=5057e59a-3e2f-47fe-a3d1-1c935b728378"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785854453.pdf?alt=media&token=56f35f81-2acd-42d5-856c-226d48bc0aac"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785923545.pdf?alt=media&token=0113698a-5e49-48ea-ab8f-01ca7a0d7571"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785999391.pdf?alt=media&token=8828f52d-7b12-4bb6-b377-1d0c040aa15d"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786064330.pdf?alt=media&token=b9e94723-8e99-41ac-b63d-9f42f5a6cc38"));

        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786150438.pdf?alt=media&token=435cf621-3777-47ee-b2c9-6a1650ca7c1d"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786215030.pdf?alt=media&token=67214285-e228-446e-9f8b-d616711fbeaa"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786279330.pdf?alt=media&token=5d801f95-c6b8-4a3a-9b66-1e96934b7ef2"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786506327.pdf?alt=media&token=4436cb03-2329-441f-8a4f-bb96c4d36eda"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786653279.pdf?alt=media&token=0405fca6-cf30-4f14-bed5-7bc4b1bfa1b2"));

        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786865228.pdf?alt=media&token=3000fe84-2aef-4dd2-b10b-1796239c7c88"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787275909.pdf?alt=media&token=2643b914-de90-4f0a-9cc6-fd2af44c4dab"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787339609.pdf?alt=media&token=c2b9838e-4b70-4e35-a2b4-cabcf1c22678"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787408135.pdf?alt=media&token=874a1253-622e-414c-b08c-d48c405f9712"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787474897.pdf?alt=media&token=9f006056-6dd4-469b-9b9f-ed071f105594"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787539573.pdf?alt=media&token=12173148-9d76-4952-b4c1-a86c35e27248"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787616593.pdf?alt=media&token=57b5c86f-b9ba-455c-b082-0b53a20a4c26"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787690014.pdf?alt=media&token=e464b190-12c5-48ec-921c-fa282cf5cb9c"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787823789.pdf?alt=media&token=abfbf13f-eee8-43ec-8cce-2dbd96854969"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787885780.pdf?alt=media&token=17e31efb-ab6c-4708-b651-467ea0cbb998"));

        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788019489.pdf?alt=media&token=3d8bd55f-03f3-4de4-9d8e-e784f72f810e"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788089519.pdf?alt=media&token=f161ddc4-2fb3-4e91-bb43-cf5cce1d507f"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788158489.pdf?alt=media&token=4a8942ff-db8d-4ec3-b4cf-74a25b6fce3f"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788221413.pdf?alt=media&token=144b2cc6-20ff-4615-9cf2-71d599e0eb1d"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788346376.pdf?alt=media&token=aeb1dfb2-3537-4b1f-af38-7eabfb7679bf"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788423860.pdf?alt=media&token=c10bcf2e-e2b2-4657-a4a0-fc39b848c6fb"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788711071.pdf?alt=media&token=f62affb5-df44-4fca-804b-776fa45317dd"));

        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788814849.pdf?alt=media&token=8e39114e-8b6a-4692-a914-4c15f0ef11ca"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788950163.pdf?alt=media&token=fc3ca333-0e0a-4f6d-8c85-709bd6b893eb"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789032909.pdf?alt=media&token=f3bedc49-71ba-4e79-89b9-b88ed54d5d66"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789178118.pdf?alt=media&token=57294203-4cf0-4624-93d7-4c55c4b32f63"));

        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789335701.pdf?alt=media&token=351cfecd-6154-4e02-b3bf-2904ac0d3885"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795683215.pdf?alt=media&token=20059996-4b12-4fb4-b887-961cb6e25b7d"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795762825.pdf?alt=media&token=462eeac6-04ee-4c7d-a644-47b3fdcf8716"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795834198.pdf?alt=media&token=61a7537a-806c-4fe6-9aef-5fd62ea6cd0c"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795896316.pdf?alt=media&token=615385ae-5042-424b-96a9-d4ca726b6504"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795958003.pdf?alt=media&token=7947cab7-03a2-43e5-9b9e-578c26935da9"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796025841.pdf?alt=media&token=a266d42c-31ff-4cac-b098-110a25dcd8a0"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796100298.pdf?alt=media&token=b9c5896e-9bad-4a09-aa54-21384afe04a1"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796161056.pdf?alt=media&token=f1d3535c-2840-41f6-9d91-b51ea98293b1"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796281735.pdf?alt=media&token=82a632c6-7129-4942-8029-4e90fdda3d37"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796363530.pdf?alt=media&token=07bec9c7-c2bf-4936-9684-cdf604bcf28d"));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796420416.pdf?alt=media&token=f51775a3-342a-4bbd-9ef8-7fc4c4e23442"));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796492522.pdf?alt=media&token=4ee95e5d-a6da-4c56-bf81-c3c69520a3e5"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796561119.pdf?alt=media&token=0a574969-b940-4239-be2f-d0d478397df1"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796622984.pdf?alt=media&token=b698b20e-c203-472c-acaf-bfb6e2886c4e"));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796689208.pdf?alt=media&token=31c99caf-3987-4642-bb33-93410c26f85e"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796755161.pdf?alt=media&token=4298f142-f968-4cb0-b40b-aa9e087201dc"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796838261.pdf?alt=media&token=3ad31f14-ade8-41c7-adf6-84dfd47726c3"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796903748.pdf?alt=media&token=d07571e4-fbcb-4418-be55-148d48610f25"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796977354.pdf?alt=media&token=3af12cf8-3dc0-4be7-8451-74900371e825"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797060406.pdf?alt=media&token=24d37789-4cd7-47ea-83f2-8b1e629dbb4a"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797137336.pdf?alt=media&token=20b7c04c-f927-4e0a-bf88-6336fb830913"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797202047.pdf?alt=media&token=8958824b-905d-4657-8301-6feaff705844"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797291835.pdf?alt=media&token=730c6eb5-138c-4472-94cf-2294349a38d9"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797355692.pdf?alt=media&token=9872c399-0759-4d6f-8c2b-dcaa82de7cc1"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797427414.pdf?alt=media&token=793f605b-f46e-4c8c-822f-8a260dc22cb2"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797496340.pdf?alt=media&token=b1c7b383-e63e-4713-b900-a32172a962b0"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797554548.pdf?alt=media&token=7e8e039d-8b30-40bb-a7a7-43bb3c1f035d"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797618199.pdf?alt=media&token=75980380-8aff-4e44-af3c-1dafe6ecff2b"));
        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797679555.pdf?alt=media&token=16a0d61f-0ee1-4538-af9a-c31f2486d24d"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797809627.pdf?alt=media&token=f6b14954-e68b-4b0f-b2eb-f569a6b5ef57"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797865680.pdf?alt=media&token=6610a097-5bb5-4be5-baba-b88ed96cdacf"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797934819.pdf?alt=media&token=e4e93ed3-e441-4994-9db8-a2fefbc481fa"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797986119.pdf?alt=media&token=16233b2d-b883-48b1-a31a-5fd856334f45"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798046175.pdf?alt=media&token=f7f202bb-545e-4dbe-b15a-f338a2e8be9b"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798110955.pdf?alt=media&token=2b11779c-dffb-4906-9cb3-722817b8c728"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798232724.pdf?alt=media&token=cffec09d-1592-4958-927f-43cdf2b807ac"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798295794.pdf?alt=media&token=ec61328f-88b9-4c11-8ed3-d14e9633d830"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798356555.pdf?alt=media&token=5fbcae53-fbdb-422a-95f7-a3b8fc5b4100"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798417983.pdf?alt=media&token=95527b30-bae6-48e5-b8d4-419f234586a7"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799146920.pdf?alt=media&token=3721fc4f-8dbb-4fa5-9e3d-316d6c9959da"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799322836.pdf?alt=media&token=b9a2ab04-a5e4-42fa-8f5d-e1f749edb3c2"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799383701.pdf?alt=media&token=0ce17d59-ff80-446e-a6e1-60fc1ed15a71"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799461916.pdf?alt=media&token=3bdfb512-41b7-44e6-b1eb-97c083714d31"));

        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799590915.pdf?alt=media&token=9453d74f-5cf8-4558-9d2f-1fc52b55f297"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799678958.pdf?alt=media&token=6d903fa7-f99a-4a39-8811-87a22aff06c7"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799763231.pdf?alt=media&token=10961965-18f3-47ef-8bee-b65407ee903a"));

        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799885686.pdf?alt=media&token=d5fb6f2f-7b76-4553-a131-c2069e723fa2"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803478527.pdf?alt=media&token=8c3337a2-45c2-4b5d-8907-ce709668df3c"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803544903.pdf?alt=media&token=5f56892d-5d3b-4913-9a85-cd2bd0957d10"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803606766.pdf?alt=media&token=9b1e9f0e-d1ec-4d85-b597-f8eb90fdbb44"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803670841.pdf?alt=media&token=a853b9f7-a8f7-4f2b-a8ab-ad514304f175"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803730461.pdf?alt=media&token=b0c21766-2dbd-4da7-8fd5-8c25f8924f63"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803786881.pdf?alt=media&token=cb87c418-3845-4b31-bb0d-bb6456955488"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803843504.pdf?alt=media&token=8b2445bf-f9b9-470c-b886-456dde6bd5f1"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803909434.pdf?alt=media&token=9af42c5b-7e0f-4564-aba7-63c4fb70cc04"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803966024.pdf?alt=media&token=2682124a-e0d3-4ebd-958d-dcdaf9d54019"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804026786.pdf?alt=media&token=da520460-a1d9-48fb-b0f5-334d3e365774"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804097298.pdf?alt=media&token=87a33f48-e086-47d3-8406-3b931e065c50"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804165472.pdf?alt=media&token=81af8bd5-0197-43a0-9b70-e40f51722f68"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804239294.pdf?alt=media&token=37e6e3e9-cc98-45cb-91b2-5fd32095aece"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804297399.pdf?alt=media&token=7b2a7bf3-a64a-493c-85f8-c2e5b6302ad8"));

        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804360894.pdf?alt=media&token=b66eaf35-c090-43b7-9937-f0199f7c6f2a"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804427614.pdf?alt=media&token=007b9326-953c-4b5e-8221-bbe636b4ad77"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804490243.pdf?alt=media&token=92cc477b-6ab4-4916-a6e5-8e467feef5e5"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804571407.pdf?alt=media&token=1c9dcb49-ac97-4de2-82c0-4fb511fa6cf8"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804644409.pdf?alt=media&token=de2c0b58-79c5-4aca-8c6e-376892deb3d1"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804709041.pdf?alt=media&token=e0738f3d-ae32-4f8d-a42d-f4ca11987ad3"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804777581.pdf?alt=media&token=7f0e3075-65cf-4320-addd-8f0d0b7bfe77"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804889126.pdf?alt=media&token=602ed10a-2bb6-48b3-a1a9-86aa2f9f5755"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804962231.pdf?alt=media&token=fb85638f-29ec-4f19-9176-91d4a3be04be"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805041354.pdf?alt=media&token=ce2bd833-e2a0-49da-9d66-0fddc333a172"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805105553.pdf?alt=media&token=937736b5-1a0a-428e-8bd5-f1f2ce1f1d09"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805184591.pdf?alt=media&token=99dd2ec9-d26e-4daf-bff7-496b475dce3e"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805241892.pdf?alt=media&token=c4204f41-f4d5-4d61-8179-dd837775c0eb"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805295857.pdf?alt=media&token=7706378d-f986-437d-b715-c384be657526"));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805353197.pdf?alt=media&token=dfe350e9-6f32-4e1b-98de-57eabc737f1f"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805421337.pdf?alt=media&token=e329624b-70b4-436a-8c47-647e1156ec38"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805488353.pdf?alt=media&token=7468232b-31d5-4a70-b196-28f2a5f72a06"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805548475.pdf?alt=media&token=2c2165b2-e5d7-45a1-bd1a-c5aab720d882"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805611445.pdf?alt=media&token=c72e94dd-9e9a-4e6f-bf7e-8496983b78e2"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805678321.pdf?alt=media&token=c0dfb441-35d9-4c3e-8d00-988db35309e9"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805767154.pdf?alt=media&token=691c174f-d241-4843-8ea1-a959d7dc5dbc"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865724271.pdf?alt=media&token=5764cb5c-8660-4708-a493-3e35bf910b0e"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865817287.pdf?alt=media&token=7ff6addf-42a4-4bd9-b72f-efff2c8ef4e7"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865905528.pdf?alt=media&token=7be6a000-cf08-415f-b51f-73213ddbcc75"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865967164.pdf?alt=media&token=e91c1365-891d-40d3-8ae8-7aec797734ad"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866030084.pdf?alt=media&token=823b82f9-d9d5-47e6-a6ca-c9f468541ae6"));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866094806.pdf?alt=media&token=88149ee1-4ef0-4b21-bec3-c5edb0adc4ba"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866202655.pdf?alt=media&token=a8736a1a-0d9d-4c14-b6d4-245ec4a61560"));
        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870783461.pdf?alt=media&token=61d4d091-80d1-4ebb-a5e4-7d4aa891590e"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870834264.pdf?alt=media&token=a4b16eaa-f154-4a85-a92b-0eb3331a00cf"));
        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870927651.pdf?alt=media&token=10bc5e7f-9910-44ab-8397-b5418233bf13"));
        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871003287.pdf?alt=media&token=722f199f-061a-45af-bbbc-c9d9bd4afa43"));
        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871171219.pdf?alt=media&token=26f1866d-7d1a-4ee8-95c2-07a5919d9b98"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871229870.pdf?alt=media&token=51db1214-cc62-4410-99ee-ca359f1198c5"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871294270.pdf?alt=media&token=354f533b-5c68-481e-8ae0-ae46ec9a2fd6"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871360467.pdf?alt=media&token=01a95577-1c6f-49d2-bf8c-51871ac73ac1"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871421188.pdf?alt=media&token=cbe40a05-e811-47b2-807b-33d1708493c5"));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871488533.pdf?alt=media&token=6c15957f-be80-41a2-a906-c2af86fa297c"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871542384.pdf?alt=media&token=dc489a50-3774-4dc9-bbdd-4c8a8cbec2af"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871619383.pdf?alt=media&token=bf32a2e5-d471-46d5-96d3-b33f8e8248e8"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871713513.pdf?alt=media&token=5e096829-73c9-4577-bbb3-c29c2bf93b3b"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871786985.pdf?alt=media&token=169a8d3e-ff2e-472a-9372-5f412081dfa1"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871844821.pdf?alt=media&token=e3abdb71-5291-4fb3-a961-4b82a7fd7569"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872033831.pdf?alt=media&token=3d07f283-ec80-41e3-9285-01b24c44c70f"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872086972.pdf?alt=media&token=5cba74f4-9744-485f-977f-b4bac8aa17fa"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872152103.pdf?alt=media&token=a6ac39e9-13a5-45a9-ae41-a166f20afebf"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872215313.pdf?alt=media&token=b2ddd5c7-e66c-4a81-b4bb-36d7b68b610b"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872345705.pdf?alt=media&token=cc8f836e-1613-4d6f-987f-2170dda3c02e"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872543651.pdf?alt=media&token=3d0fbdf0-e7f3-4b70-892a-2d54529c4fe1"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872495879.pdf?alt=media&token=3067ac9f-af19-4430-a24f-8efc56f00da1"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872598511.pdf?alt=media&token=de6746ca-78d1-4f24-87f5-d1a2c201a5b7"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872660357.pdf?alt=media&token=d6cb626d-9bf7-49ba-b2d9-75cc4013d9c3"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872717676.pdf?alt=media&token=9d777c04-59ae-4901-a2d2-1a38ac35a3a9"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872813249.pdf?alt=media&token=4bb58e27-94ac-40df-9826-81ae5c51ab22"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872887861.pdf?alt=media&token=262bd905-ced7-4c68-8030-a826d75fa33d"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872969942.pdf?alt=media&token=e6bc3605-801c-4607-adde-58ac4ebc9553"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873035965.pdf?alt=media&token=097406ef-b938-4b1e-8149-0ecb7bac2020"));

        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873092526.pdf?alt=media&token=068e9305-8e12-4510-b80b-7b848c03d388"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873148820.pdf?alt=media&token=00e9fd5b-3023-43f8-8ed7-29b8ac23b286"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873205489.pdf?alt=media&token=d8f69c46-c513-4a14-8228-22e4c7d2abdf"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873259082.pdf?alt=media&token=f2ac7940-65de-4739-9e20-aa44408436e8"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873324795.pdf?alt=media&token=429c8166-b9aa-4d07-8f89-968b0577f7cb"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873379514.pdf?alt=media&token=c68b847c-fbc9-47fb-a0f4-8a2693d60e5e"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873440697.pdf?alt=media&token=02bd5bd1-770c-49ea-a7fc-8e9686d2c261"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873503093.pdf?alt=media&token=2cdd32b4-51ce-453b-9ede-b785c640c6f5"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873566846.pdf?alt=media&token=a8f5fbbd-edb9-40ee-a5fd-1f8e7617d5d4"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873642731.pdf?alt=media&token=8a0ffba4-2d38-49a3-a67b-e4479fba9a81"));
        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873702224.pdf?alt=media&token=c85129d9-6da1-463c-bf31-472ee8ec64de"));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873744523.pdf?alt=media&token=44cf5cb7-ce3f-4cd9-bf21-58ba9be9c350"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873804433.pdf?alt=media&token=34ff44c6-8209-4c73-b036-0437548e5b91"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873868090.pdf?alt=media&token=230a36a0-d896-4640-9bd3-935836304fe3"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873929142.pdf?alt=media&token=3d558dbf-69c3-4b84-8f9f-7e3f72868349"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880021520.pdf?alt=media&token=7c3d7685-1fdb-4c56-a56f-db29773545e1"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880095183.pdf?alt=media&token=e2d04bab-6224-4706-9821-8269f7080052"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880165037.pdf?alt=media&token=9f95a7a0-2220-4d56-a785-2824e0b17303"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880224438.pdf?alt=media&token=e7b4e953-644d-4acb-8019-158ac4271d45"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880345807.pdf?alt=media&token=7248b403-6135-4482-8a7c-1f5d9bf95ef2"));

        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880407691.pdf?alt=media&token=071de33c-be77-40d1-a853-d55fcc28b131"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880461663.pdf?alt=media&token=f8d92d55-3c2d-4d39-a375-35dd3bfa10b4"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880517736.pdf?alt=media&token=046ee1fb-af1d-4029-8393-a467a0a407b8"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880571589.pdf?alt=media&token=ed27e1d2-b912-49c4-b152-d7197a4945eb"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657881722091.pdf?alt=media&token=a0dd4208-a392-4963-94cd-daddbea85479"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657882835064.pdf?alt=media&token=c2baaaed-8e8a-499a-9090-10ce8a9fe6d2"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657882983549.pdf?alt=media&token=216d7117-1adf-4051-9950-10cdba1effab"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883047186.pdf?alt=media&token=4788e936-8e02-463c-b4f7-6242ea7979fb"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883166943.pdf?alt=media&token=090ea3bb-ffc2-4829-a30d-b6bb28694266"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883244998.pdf?alt=media&token=88465cbf-a181-4571-b075-acbabaa0f084"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883336045.pdf?alt=media&token=0bc94de3-ebcc-49b1-b811-1bd40c72045e"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883450759.pdf?alt=media&token=ddf09919-42a9-46ae-8e88-5a6b4af552dc"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883511192.pdf?alt=media&token=95ab77dc-954f-4e94-8ce5-5fd124859c90"));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883569245.pdf?alt=media&token=bad7ebd8-57a7-44ea-b773-ebd19b8bd6e4"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883631896.pdf?alt=media&token=5ed8c8aa-96f7-4534-b77f-6272a9ffe993"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883691208.pdf?alt=media&token=1c5d2dfc-ee13-4947-ac14-3e42bb6d1a00"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883759004.pdf?alt=media&token=539a9c08-1ac2-44c6-bc53-cda8811fe34b"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884017042.pdf?alt=media&token=41576b32-20fc-4097-b9fc-badebbdf5d89"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884087184.pdf?alt=media&token=7fd74936-5cad-44c9-8f41-b48002f31dfd"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884151521.pdf?alt=media&token=a077a192-5c1e-49e0-a833-5cb6b7da1ead"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884217228.pdf?alt=media&token=eb34f99a-ad5d-4ad7-8cb7-2d70bea999ed"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884273076.pdf?alt=media&token=0055b346-3437-4fe8-9d51-97c4b93a9246"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884273076.pdf?alt=media&token=0055b346-3437-4fe8-9d51-97c4b93a9246"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884389912.pdf?alt=media&token=0410d623-a820-4f66-9060-042852f87511"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884492771.pdf?alt=media&token=03f91514-516a-41bd-b358-5b815ec382df"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884575059.pdf?alt=media&token=2f8b7235-c165-41cb-8b82-2dab0eddbf86"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884643055.pdf?alt=media&token=c976bc01-2109-4b85-9489-3a9e49bfc46b"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884697915.pdf?alt=media&token=853d9e53-1f71-4159-a5a0-2abe9ac121c0"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884766580.pdf?alt=media&token=1cb17004-d860-41d3-80a4-c1389ac8310b"));

        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884852923.pdf?alt=media&token=a364c93f-cab3-4230-9df9-0b1dd0fcfc64"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884919571.pdf?alt=media&token=ed8c1a03-f93f-40a9-b665-1aabf5c2c9eb"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884984123.pdf?alt=media&token=4f495a50-9fcf-4c69-89a2-d70d45cf8b5b"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885052836.pdf?alt=media&token=a2f3c9a5-9192-48ac-b1d7-b986b866f31d"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885116268.pdf?alt=media&token=379530c4-4ef4-4b76-a013-c489837582e0"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885178116.pdf?alt=media&token=76339ee0-e8cc-4792-b7b5-2a968eb792db"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885238369.pdf?alt=media&token=aa4dfb10-27fc-486f-a6cb-a0ed267f58c0"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885295782.pdf?alt=media&token=d27a4e97-ca44-457b-83c4-91db64e5d748"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885357087.pdf?alt=media&token=482ed9d4-091a-4c72-97e9-bd57ba3bb490"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885443475.pdf?alt=media&token=2b2c0cfd-d884-425e-ab34-de964bd823b6"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885499317.pdf?alt=media&token=a414681b-3485-438c-890f-ed36c4c82ea8"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885556074.pdf?alt=media&token=4450419f-68e6-45f1-adb0-8a118568ccd4"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885627325.pdf?alt=media&token=f8f99f57-44a6-4f95-add4-93dd917ca776"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885695952.pdf?alt=media&token=54f38aa4-253d-4aca-9700-b5a03f9c8833"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885760395.pdf?alt=media&token=27fd7ec4-2a5b-4b3c-8a59-af7f0141e414"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885825385.pdf?alt=media&token=8d69f9e2-5780-4398-b345-26ce6c501d46"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885871675.pdf?alt=media&token=3e015885-335c-4b8b-b60a-4d92f7979a4c"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885974118.pdf?alt=media&token=84db73c3-928d-4336-8253-f7c5529b5dd1"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886067543.pdf?alt=media&token=ff0804c8-1bc3-453b-9e97-d40461ce3493"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886127059.pdf?alt=media&token=7c2f944a-277d-4ecf-a005-3232e48ad344"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886195520.pdf?alt=media&token=81c58867-73b7-4da7-8dff-1259b50af7dc"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886264189.pdf?alt=media&token=9e96b9bb-4f56-4105-81e3-5bdd04198913"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886337870.pdf?alt=media&token=693d1dee-1369-422c-aa7e-1ec5656ce297"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886472955.pdf?alt=media&token=842bd26d-5e77-4238-af11-6a1cf20b30bf"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886540799.pdf?alt=media&token=9999d961-4d7c-4f99-953d-8d5cd0ffe87f"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886705630.pdf?alt=media&token=d30d40ad-9a97-4fbf-904a-0bb91d244f43"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886782521.pdf?alt=media&token=4f2af7d2-c3f8-40bb-a809-20e6a078e444"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896847108.pdf?alt=media&token=04c0bf00-cc50-4b1b-8775-a665957c72f8"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896948933.pdf?alt=media&token=c4d3cfba-c4f5-4a72-a140-a4023922677b"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897041817.pdf?alt=media&token=5cbf5912-6c7a-4285-be88-acc902bb3d4b"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897250233.pdf?alt=media&token=0b5aac0f-c5b4-462a-8d94-66f99b212fb3"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897326430.pdf?alt=media&token=9853d177-2447-48e5-95ef-dea033c914c7"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897693888.pdf?alt=media&token=cbcc1dfb-b9ef-4c52-aae6-f326eec64657"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897756728.pdf?alt=media&token=71d47dcd-4573-4eaf-9df3-1abe8ff80a95"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897843452.pdf?alt=media&token=3588be39-ba2e-4226-9088-6ad41266077a"));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898575739.pdf?alt=media&token=6aa9deda-ac81-40ab-8956-7aa446891cab"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898748713.pdf?alt=media&token=5834d8d6-8699-4458-867c-de33ec9c22af"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898846289.pdf?alt=media&token=94d4f773-f972-496c-8f6d-d0425e5e9e3d"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898927911.pdf?alt=media&token=ccda1ea3-2d46-4f58-a2c5-8b1ba44a90b1"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899006562.pdf?alt=media&token=4df5a7fc-fc21-4cae-b574-8e3547729d7d"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899061456.pdf?alt=media&token=46f83e60-2e2b-4bbf-a473-eba6c55d8357"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899151757.pdf?alt=media&token=a2b88a0c-fdcb-45cb-9dda-25524d268c79"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899229693.pdf?alt=media&token=e0b2c32c-35ac-4a3c-816f-cf8ef0364d73"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899314926.pdf?alt=media&token=3217ce19-f755-47db-9c7e-74f9beffa24d"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899390271.pdf?alt=media&token=c1b2bd6d-3ea1-4dde-823b-9fb8a0c71d3a"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899469894.pdf?alt=media&token=12c214be-21f4-4d03-9f45-e8f856a9b361"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899554264.pdf?alt=media&token=5f52fdda-cc83-4b0f-8b2e-5f545cf7d372"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899628862.pdf?alt=media&token=47e855c0-c4e7-4176-900f-4e942a5e764c"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899702773.pdf?alt=media&token=0a5729dc-901c-4687-a4d6-68c84092e352"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899796188.pdf?alt=media&token=ba0c3a2f-4345-4512-9e9f-6c821458358d"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899883475.pdf?alt=media&token=1a08e9d5-bba7-47b6-81db-dc90304bbee6"));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657900999802.pdf?alt=media&token=b8fed84b-dc7c-4e20-a31e-d49a53f6e7aa"));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901078902.pdf?alt=media&token=fda8a38a-3710-4cde-b8c1-52b335d1a294"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901167873.pdf?alt=media&token=52ebbc11-f565-48bd-821e-7419f6e38514"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901250552.pdf?alt=media&token=13ac05b8-bf97-4d43-bd51-05e30c9857db"));

        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901327117.pdf?alt=media&token=8adcc4e0-0963-4dde-928e-af9c5903ae8a"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901404286.pdf?alt=media&token=fa338982-85be-4333-9201-b1c9e2f961c9"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901477945.pdf?alt=media&token=9c0235c9-7198-40d2-9fe2-5e31370d651c"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901585816.pdf?alt=media&token=5093b18b-482b-44c7-a416-d13ef039719c"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901685505.pdf?alt=media&token=7c8c051b-53ce-4514-b758-55050131b0ae"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901770058.pdf?alt=media&token=711264b3-9943-464a-be5b-2d6fbc0da6a9"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901893438.pdf?alt=media&token=24fbf091-71b1-421e-bd93-bc1f12a5300f"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657902149172.pdf?alt=media&token=7d0c3656-1e1c-4154-8e32-7d5d4cf77797"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906778806.pdf?alt=media&token=8757f6e1-f5a7-4f85-bf01-b16c3a979ab5"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906863442.pdf?alt=media&token=ca42b18e-088a-40ed-9a69-39d017d4656a"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906937275.pdf?alt=media&token=19de911c-4c51-4d32-b5e0-c4acd1d3d319"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907004490.pdf?alt=media&token=c0f1e998-946b-49a7-ae3d-38a6e9c71d2a"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907069520.pdf?alt=media&token=cbfd79be-9329-4933-8e18-d07981106f74"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907119329.pdf?alt=media&token=192147df-9b99-44d6-9a3e-ece51b23eeb8"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907197234.pdf?alt=media&token=dc532e50-7f8a-454f-9de9-d1c356f97c3a"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907268805.pdf?alt=media&token=43de0c23-381c-4c4d-bb2c-fd7c8669a7e0"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907337469.pdf?alt=media&token=fe7c9a80-4e0e-40a8-86df-b0549a2653a6"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907401530.pdf?alt=media&token=886e0a77-ed4c-4d2e-9663-1eeee23ab455"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907460305.pdf?alt=media&token=43c517d5-cf13-48f4-bef0-f6f0ee492a04"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907526639.pdf?alt=media&token=98db580a-c4be-4db2-a566-2de725624b09"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907587766.pdf?alt=media&token=503021c0-96b0-4226-942d-fef5d18420f3"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907642178.pdf?alt=media&token=c859985a-ce43-493c-a170-e8f287ffe60f"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907718459.pdf?alt=media&token=92dd415b-f218-4dcc-b7a6-2c465718a603"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907796801.pdf?alt=media&token=ec0c9c64-7d00-4715-9795-daf29910c8fd"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907862707.pdf?alt=media&token=6268b75a-dfb2-4a8c-99f3-c01886de8ae3"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907923050.pdf?alt=media&token=42276a63-8e8e-4655-b404-37c63e4fd76a"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908013728.pdf?alt=media&token=0c839df3-db00-442d-8229-2b963abbbd00"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908074393.pdf?alt=media&token=1516278c-a3ba-4ec6-829c-7f2c40dd0084"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908133860.pdf?alt=media&token=71435f4a-a289-4e00-8ef4-defb09d8f1cc"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908193753.pdf?alt=media&token=4d429467-6a0f-4583-9bbe-369d473f4400"));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908285369.pdf?alt=media&token=02fba31a-78ef-4038-8002-675c6a574609"));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908335958.pdf?alt=media&token=bd688c6e-3331-4fce-953f-0d04cded32ce"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908398617.pdf?alt=media&token=8a74a5c4-f795-440c-80d7-b9c65f56b12d"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908458306.pdf?alt=media&token=59e30f31-a915-415e-95e7-0d4ad619c463"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908518603.pdf?alt=media&token=8cf725be-ef0b-4db2-8cc4-37068160240d"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908577371.pdf?alt=media&token=ed8d076f-bdfa-4e8c-a171-ea645d193021"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908645092.pdf?alt=media&token=2a5852eb-d1a3-4c54-9557-0547d1ab1ddf"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908706368.pdf?alt=media&token=7fb3f256-c4b5-4126-823b-e09e5511928a"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908796781.pdf?alt=media&token=45ba9e24-009c-4eec-951a-06d54814850d"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908861163.pdf?alt=media&token=42a35f61-02a0-4ee4-ad73-67b9a8534d05"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908922437.pdf?alt=media&token=bb28f8da-0f9e-4c15-bd05-0d5a5d02f94a"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6839,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908983237.pdf?alt=media&token=ef86c150-0b14-4348-b6be-eb942a7f7bd0"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909042095.pdf?alt=media&token=82179457-d39e-4f82-a9f1-91dc444763d8"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909101865.pdf?alt=media&token=42615cf7-93e4-490c-858b-83289d532cd3"));

        progressDialog.dismiss();
    }



}
