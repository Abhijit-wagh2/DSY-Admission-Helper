package com.aashviit.DSEAssistant.ui.home.cap3;

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

public class Cap3_2019 extends Fragment {
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
                Log.d("Text_Change_work","Adter text changed working");
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
        db.collection("Cap32019").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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

//remove
    private void initdata() {
        userlist=new ArrayList<model>();
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531731563.pdf?alt=media&token=b82f00fb-6547-47a4-a1e6-d8cda7634b71"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533384407.pdf?alt=media&token=bd67c027-aeea-4b8a-a02a-563f0823d907"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533412849.pdf?alt=media&token=a57762e0-4e5d-4a37-a52d-0c9d7df09928"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533438973.pdf?alt=media&token=1b99d0ec-647d-4c5f-9551-537a110b5024"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533470837.pdf?alt=media&token=fe6eb595-3c10-42aa-a20c-7ae27fa1e797"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533492505.pdf?alt=media&token=1e9df268-d309-4da6-a4ea-7678185ed590"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533517244.pdf?alt=media&token=c64382e3-c814-4645-8eb0-5558245955e3"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533541387.pdf?alt=media&token=b8393b36-b617-48c1-b840-02f9b519afc4"));
        userlist.add(new model("Paramhansa Ramkrishna Maunibaba Shikshan Santha's , Anuradha Engineering College, Chikhali",1119,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533566116.pdf?alt=media&token=269d987b-0070-4d70-a646-575ad8b06ae5"));
        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533586621.pdf?alt=media&token=9ae43a8d-1f7e-443e-a8a6-d06e08ef7dea"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533610355.pdf?alt=media&token=cba33d50-03c7-4550-bb9d-e852ac1fd21d"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533631560.pdf?alt=media&token=a8abcc41-b3e8-4944-b1a6-c5c95c58d937"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533710166.pdf?alt=media&token=2eb092c6-2c27-4928-9deb-357f59a3737b"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657533771081.pdf?alt=media&token=a4033cd4-d0ed-43b8-a325-0a2b9899bed2"));

        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657538820426.pdf?alt=media&token=be0b5e01-21f6-4169-bc81-fb9b64858a7d"));

        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657538959850.pdf?alt=media&token=2f5203e9-a171-4ff4-8e18-b28199568827"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657538983454.pdf?alt=media&token=d472a0db-cc24-4105-9e1b-b06fbd38933c"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539026750.pdf?alt=media&token=c30d20af-2550-4a9d-8d9e-b80a0523e75d"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539058821.pdf?alt=media&token=261acd6e-3733-4104-a995-105b41381e2b"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539082757.pdf?alt=media&token=199adeb8-7bca-4d3c-960f-6059f4c4eff3"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539116184.pdf?alt=media&token=88c94d6e-aaab-4261-b03e-7d0df2a94828"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539175293.pdf?alt=media&token=fde355bb-887d-4138-8b5b-bfc2b34c6fb1"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539196864.pdf?alt=media&token=76d789e6-f2fa-4987-9c22-9ee349a74186"));

        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539233152.pdf?alt=media&token=563f11b0-bd99-412e-bdee-b5dfc04f793a"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539262443.pdf?alt=media&token=cc375cfb-a45f-4973-805d-5493aa538572"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539284681.pdf?alt=media&token=c62b7ef6-4fa9-4801-896e-07140340c9e6"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539310259.pdf?alt=media&token=e67e53fc-91d1-4259-93f9-4ab1ae525392"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539387350.pdf?alt=media&token=847d81c3-4a87-4302-82ec-5dd537ed8358"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539412709.pdf?alt=media&token=6f98f397-5414-437b-97b6-7b0e6f5251e1"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539513585.pdf?alt=media&token=8ba9ecd8-459f-4366-b3aa-e39c77937721"));

        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539541548.pdf?alt=media&token=589de5de-dc97-459b-a0fd-bfdfd80d2982"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539563237.pdf?alt=media&token=039c8a78-c8fd-4c65-942b-81ef68e1fa63"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539609824.pdf?alt=media&token=0a768c36-4e0f-4092-a34c-4be2eb41aad3"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539673808.pdf?alt=media&token=18429a2d-f9a8-4433-ba91-26f388f0aa0a"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539701772.pdf?alt=media&token=81146795-397f-4c21-b1c1-9077a89c90a3"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539731987.pdf?alt=media&token=2960949e-d2f9-43b0-830d-00a5aa6ecbe0"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539776223.pdf?alt=media&token=9c9d3a14-8fc6-4f46-acf1-8f7702f4ce8e"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539808716.pdf?alt=media&token=9a558090-58eb-433e-a73f-37d855d0e177"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539835937.pdf?alt=media&token=e17f05e8-68f0-4336-9cb0-df64b093389d"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539865984.pdf?alt=media&token=c45cd220-176a-4ed0-a673-fa7e9a6f0b83"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539911355.pdf?alt=media&token=300234bd-432e-46e2-88a3-c759662773f5"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539940654.pdf?alt=media&token=34bb0c0e-11f8-4ba9-bad7-dcabdc06709d"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657539986405.pdf?alt=media&token=f0c0fed1-bcd4-44f4-ad0a-83669b7b10d3"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540086537.pdf?alt=media&token=9fb2c520-4e21-48f1-8215-deff509b4250"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540113693.pdf?alt=media&token=f0b85d05-e9c8-45aa-9e59-a7cf3eb52cd5"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540153069.pdf?alt=media&token=81f88618-701f-4d83-8b16-2c15fa8c3b15"));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540183517.pdf?alt=media&token=167eb301-9651-4de4-a1fc-3f5894758cfe"));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540222056.pdf?alt=media&token=75926155-538e-44a1-8e4f-88eae2ea8890"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540253087.pdf?alt=media&token=6d1c7421-b8a5-409d-b2e7-ac0ce3b70ef5"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540280611.pdf?alt=media&token=65a09630-e40d-4fe8-91c2-53c412234b56"));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540306971.pdf?alt=media&token=84dc27e4-26d2-4321-8404-e9008a80d67f"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540331191.pdf?alt=media&token=934213ad-d8be-4f7c-ac9f-60c9ee6f1f34"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540356702.pdf?alt=media&token=6e7cfd0b-373a-4031-ab21-501f12206a68"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540392952.pdf?alt=media&token=ab051000-20ed-4fb0-9bd6-f8da1b9ef1f7"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540415163.pdf?alt=media&token=3a1248c1-ccf7-4397-b75d-e0cd3d5a0c83"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540448610.pdf?alt=media&token=99ec6776-5900-4eff-a4b1-827f6485339e"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540496307.pdf?alt=media&token=d90f25b2-9a45-4807-90a9-287cb180c96b"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540522826.pdf?alt=media&token=9d797f2e-acb1-469e-9e55-2fcaf8aa06e6"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540545258.pdf?alt=media&token=ed1037cc-f626-40e2-b067-321eaf22e5db"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540573640.pdf?alt=media&token=e1b257a6-e92a-4ff2-9153-00fad04159b0"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540607707.pdf?alt=media&token=20eeff31-3dff-43bb-bd33-9440937f7d11"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540633126.pdf?alt=media&token=378c566f-cb36-456e-accd-e8b309637484"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540664231.pdf?alt=media&token=ce2268d9-5a27-4d9a-84de-fddbcdbd15e1"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540686392.pdf?alt=media&token=8c9fea59-4e17-4ed7-b1d7-4d05f68f7047"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540745746.pdf?alt=media&token=656a8fa2-0532-4b5a-a64a-dbf669cd9fa1"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540768214.pdf?alt=media&token=a3aabd36-a6aa-43c6-92be-670b65164005"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540793000.pdf?alt=media&token=30c65738-29c5-4640-a352-54d76edd8785"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540820184.pdf?alt=media&token=1a09cb48-1dd5-4464-a7d8-4102286bbb85"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540858521.pdf?alt=media&token=66f26377-7cf1-494a-b36b-63ece99c07cb"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540880970.pdf?alt=media&token=14d59add-bc4a-46e9-b973-6e29fa7ab6c7"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540929378.pdf?alt=media&token=4e2fd96c-0094-4c42-a57f-ca0e14c41386"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657540959756.pdf?alt=media&token=63a06d26-6e8b-444c-bbc2-0049d79a2aff"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541222086.pdf?alt=media&token=da3e5707-00b5-4304-85c5-4601934e3c12"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541508882.pdf?alt=media&token=f01de4da-8ff2-4353-a58e-51573834e327"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541508882.pdf?alt=media&token=f01de4da-8ff2-4353-a58e-51573834e327"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541595526.pdf?alt=media&token=b57bffb9-2c26-49d4-b135-39edae46e51a"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541625405.pdf?alt=media&token=00b6dffe-3ff8-463d-abeb-ab986521703b"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541653036.pdf?alt=media&token=b3114f94-f28c-4762-a0b6-84fb6b6b0608"));

        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541681979.pdf?alt=media&token=07c7b4e5-3da4-4833-9ecc-1caafe06ab51"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541706007.pdf?alt=media&token=a89ecc8c-6251-4fc6-a78b-d5786306634c"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541728339.pdf?alt=media&token=0b2ffa8c-c330-4994-8992-9b011c29330f"));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541754524.pdf?alt=media&token=6c4b2541-d4d0-4457-8eec-bdbceb021af6"));
        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541787349.pdf?alt=media&token=8dba3520-c2d2-43a7-a487-ac012e645faa"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541836541.pdf?alt=media&token=4d0ea1ee-6c46-40b8-b10a-480fa6b8a214"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541864856.pdf?alt=media&token=8d857000-2051-46ed-9125-3a1e33673c28"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541895343.pdf?alt=media&token=fc1551ef-c66b-4b9c-950f-daa0f9f60fb5"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541937721.pdf?alt=media&token=6c103a42-a068-4c02-a3b1-48def7ab70b3"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541968877.pdf?alt=media&token=c9ed7006-0c80-4048-b887-ccc07f3a4763"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657541999334.pdf?alt=media&token=71b736fc-0ca0-42cd-b0c9-3017915d7c09"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657542022597.pdf?alt=media&token=673ef811-771e-4112-93b8-3bef74a188d3"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657542047523.pdf?alt=media&token=d3d95c4d-70b7-4bcd-a387-fbb656991add"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657542097092.pdf?alt=media&token=bcd351ac-0d6a-4c2b-92de-684b63c523ef"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606084632.pdf?alt=media&token=bd9f36c6-7891-4cf3-9e40-60d015d3e8ba"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606119163.pdf?alt=media&token=934d3ab9-9722-446c-9df6-a5c6aee1138f"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606148844.pdf?alt=media&token=2d5c2dd9-c80e-47a3-ab4e-288b90ba2d6e"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606119163.pdf?alt=media&token=934d3ab9-9722-446c-9df6-a5c6aee1138f"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606199028.pdf?alt=media&token=c64ceb15-753e-4d0d-b171-2f82391b2b7d"));
        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606229769.pdf?alt=media&token=00a8f793-f831-4af6-8deb-61c75454027c"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606252883.pdf?alt=media&token=28870cb1-85eb-442a-8aef-2b8802f38f75"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606279572.pdf?alt=media&token=5de05b37-27e1-4c63-bdaa-d743f7643de9"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606309721.pdf?alt=media&token=8062b4bf-56cf-4b2a-b052-943edc781f25"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606335137.pdf?alt=media&token=796f55a2-ab38-4c57-a121-9cb5d24244a5"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606355881.pdf?alt=media&token=cde2900b-a733-4548-923b-e76da4d59a60"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606389499.pdf?alt=media&token=42922e6f-20a7-4a1e-9514-b33af931c2f7"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657606412836.pdf?alt=media&token=aaaf9e15-2ada-45a3-9965-ceb6e77bcda5"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657625416342.pdf?alt=media&token=320db00f-1ae9-4c0a-b69d-dfe1df5351f7"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650327553.pdf?alt=media&token=7ee135d3-8f03-4c4b-acbf-8986f1556984"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650355017.pdf?alt=media&token=48c39baa-580f-4450-a937-9afd5e72cc03"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650378954.pdf?alt=media&token=040b8ee7-b650-4e9d-86b6-0f9c8f70bda8"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650414514.pdf?alt=media&token=5ae7ea98-c02b-44d4-8a38-12aed2719b10"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650439252.pdf?alt=media&token=a180113d-43fe-4d9a-802e-5be8704ed135"));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650462325.pdf?alt=media&token=7071d7df-50f9-4fb8-9a86-1c9c8cdd6cb8"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650484991.pdf?alt=media&token=c2dcdd65-e3a8-4e34-b3d9-19263e38697c"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650510148.pdf?alt=media&token=d2e3a6dc-6ee1-4abe-8cc8-569aa2754540"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650554715.pdf?alt=media&token=2d0a948e-b114-4c39-8372-dd0654026f12"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650581094.pdf?alt=media&token=2be46127-9065-4a22-8e80-2fe396ee288d"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650635623.pdf?alt=media&token=6de0dd97-fbc9-432c-8571-9878ac431087"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650656861.pdf?alt=media&token=a98bb690-ee10-4cae-a0ba-f04c4dc2bd14"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650684249.pdf?alt=media&token=ea996329-369c-4b69-a787-fb6b9297e4b7"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650711612.pdf?alt=media&token=3f9a48be-27d6-46b3-ae49-580887862b1c"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650735143.pdf?alt=media&token=961aafb0-6b1c-4d0e-aa91-800096af94c7"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650756359.pdf?alt=media&token=015bba16-fe13-4208-8656-7fe880d3f94d"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650778378.pdf?alt=media&token=eedeaeee-5974-48a5-93bc-346ec89fe2bf"));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650808619.pdf?alt=media&token=6859a695-3326-43fe-b157-b8680f12170f"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650836895.pdf?alt=media&token=b6de4446-57e1-4281-a114-836831d57ef0"));
        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650859912.pdf?alt=media&token=a41bd2f3-e87b-40c0-926f-2fe950bf67a4"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650882730.pdf?alt=media&token=cf208147-e6f4-4a37-9678-9ba13b01dced"));
        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657650959941.pdf?alt=media&token=d964a52d-5900-41b4-b587-9b90147bbb95"));
        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651041038.pdf?alt=media&token=8490f0b5-d090-47bc-9ced-87da5bfc64ed"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651069856.pdf?alt=media&token=73c95d03-fe21-47f9-ba84-7ddadc13fc14"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651092703.pdf?alt=media&token=4e051c33-9aec-4e23-a0e6-ac862dcfe9e6"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651120224.pdf?alt=media&token=71076ded-7c6c-4b68-a4d9-48f0ff483f23"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651261030.pdf?alt=media&token=d46491f0-7648-407c-a679-5ea85b8ce312"));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651282386.pdf?alt=media&token=bddcdca8-6004-43dc-b12e-2783ecf3d5b3"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651493669.pdf?alt=media&token=abd68534-7e83-4da7-9c7b-e3aedead3e72"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651517809.pdf?alt=media&token=a79f7fe7-346a-4ed9-b506-cf03b3c25abb"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651540368.pdf?alt=media&token=431c1813-d5d0-441c-a275-84c92b6e63b3"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651591324.pdf?alt=media&token=adc58f8c-6f9a-4e7b-9feb-a38b3abb29f0"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651647409.pdf?alt=media&token=86aacea0-e47d-4865-9dae-7b4d1e042ce9"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651792195.pdf?alt=media&token=b0f87052-1c31-465d-b4c9-92a3d80d52a5"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657651813834.pdf?alt=media&token=04de299e-6ad2-442d-8998-1986cc283cfc"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657687226537.pdf?alt=media&token=fd53ba94-a3e8-4e36-8bfc-9688ba5d1401"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657701927246.pdf?alt=media&token=7cb7ec72-9a8d-4331-9881-ec3517c56b5d"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657701955391.pdf?alt=media&token=ccb5cd78-c409-42ee-a2b4-19f40fbd39dd"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657701978596.pdf?alt=media&token=49598328-16d4-47b9-a978-4536175c5d67"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702000961.pdf?alt=media&token=97c724c7-72bc-44fc-9bdb-ac34e56e660a"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702032556.pdf?alt=media&token=4d7796f6-3b2c-468e-9359-81dcfed97419"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702061289.pdf?alt=media&token=1bbf5c73-2f79-45c5-a995-48cf024e8b9a"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702084100.pdf?alt=media&token=c0b8fe4e-a3a9-458e-bd22-103d522046bc"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702119213.pdf?alt=media&token=43a2f1a1-199e-4620-bcbe-d7aa14bfc28b"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702141893.pdf?alt=media&token=f721dd1b-d413-4fd6-9b6f-42ca074ed391"));
        userlist.add(new model("G. H. Raisoni Institute of Business Management,Jalgaon",5152,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702163558.pdf?alt=media&token=922d2f39-395b-41d6-870d-04573e649278"));
        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702163558.pdf?alt=media&token=922d2f39-395b-41d6-870d-04573e649278"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702187117.pdf?alt=media&token=04834fd7-95ae-4181-a861-6eff32920f06"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702187117.pdf?alt=media&token=04834fd7-95ae-4181-a861-6eff32920f06"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702252366.pdf?alt=media&token=78b3bc46-5edc-4dec-9abb-57543bea27bc"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702278507.pdf?alt=media&token=caefd270-8f12-4d84-925e-0126bbd351c9"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702298653.pdf?alt=media&token=e0f0990b-96fb-4787-a209-9c89aeff4a12"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702331287.pdf?alt=media&token=c104ddac-d158-4f7f-a3f9-8fab6c5ec06d"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702362222.pdf?alt=media&token=1c93b01e-624e-454f-bf8e-18eace252c69"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702385540.pdf?alt=media&token=bff1e49b-fa16-4c17-8e9b-2fbbd226f8c9"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702420796.pdf?alt=media&token=b55d0712-0254-435c-a883-2e9ce71ad740"));
        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702478398.pdf?alt=media&token=78e8b7e1-3774-46d7-a1c3-324eab681cf4"));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702507074.pdf?alt=media&token=07f9c090-d400-4004-8227-83f961a7fe20"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702528314.pdf?alt=media&token=5ad213f4-290e-405a-8293-f9c34db4cc88"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702550113.pdf?alt=media&token=a1bc9f1d-74eb-4b3a-acd6-c612c2726a1b"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702577148.pdf?alt=media&token=bf1c01e7-124e-4308-8e49-ec24d57f83f4"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702600232.pdf?alt=media&token=45e33d43-9e52-449d-bad3-4e9d5c811303"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702631562.pdf?alt=media&token=c4546b4b-b74d-4244-9bf1-42c3d13f3811"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702681454.pdf?alt=media&token=4a7778ee-0157-4863-b9df-a7bc8acc563e"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702721692.pdf?alt=media&token=734076b3-bc2f-4744-8309-d11c8f13ab57"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702769725.pdf?alt=media&token=0123d66f-d08a-4acf-93b2-de6e9a3f7d95"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702769725.pdf?alt=media&token=0123d66f-d08a-4acf-93b2-de6e9a3f7d95"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702796620.pdf?alt=media&token=e4b6061e-37ba-44de-8370-d4bb9fbcf173"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702822324.pdf?alt=media&token=c92c00d3-0a9e-4980-a144-e3363a237c1e"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702845229.pdf?alt=media&token=211c47b8-a692-4308-ad64-e8f55294acea"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702869998.pdf?alt=media&token=1b6560d9-91a9-494a-8ac5-4edc3abc559c"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702896626.pdf?alt=media&token=7a7c9ccb-0a6a-42b1-bed0-2767ba0f19a8"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702921481.pdf?alt=media&token=a12fb129-547d-4bfd-b19a-2a46d02cab49"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702945793.pdf?alt=media&token=d98bb56d-66ee-4b42-a6ce-906e43c3d629"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657702967907.pdf?alt=media&token=d3733b2d-e2bd-4a99-8d18-4427ace72a67"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703019118.pdf?alt=media&token=9e628a3a-9cd8-4e63-b842-4502bb12e1c7"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703042177.pdf?alt=media&token=156fd20a-86a2-4114-8b11-e9c113215243"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703067471.pdf?alt=media&token=a0acc3d6-dfd2-41f1-9c46-acccc231fafb"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703116134.pdf?alt=media&token=fff79ef5-7997-4187-88a0-16adc009d2c0"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703259324.pdf?alt=media&token=60076752-16ca-45a4-af4e-70d169a73277"));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703414277.pdf?alt=media&token=1f1b4d78-50b1-4dca-bc92-5ecd56b94245"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703446845.pdf?alt=media&token=ec7bcb89-23c6-41bf-82ba-4ada3577372d"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703474697.pdf?alt=media&token=f2430011-9d71-4019-a4b4-0f5b7d8da101"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703496842.pdf?alt=media&token=86340d61-3f6f-4029-9d2e-2b048d9138b0"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703518192.pdf?alt=media&token=2f4acc58-a19c-41f5-8b46-ed8cca5f13b9"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703641442.pdf?alt=media&token=906930f3-578c-4472-9766-0026240faf26"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703541420.pdf?alt=media&token=8006db30-4d47-4d4d-a3ae-e538c79c6eca"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703695100.pdf?alt=media&token=b259c8ec-2895-4879-8b57-d8b2e50fda4b"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703716736.pdf?alt=media&token=538d526f-2c08-4084-94c7-c4041c58bb61"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703739791.pdf?alt=media&token=a338c005-50dc-47d0-a6a8-f9579fa3e2dd"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703762972.pdf?alt=media&token=1bce11e0-83aa-47e0-ab59-345382c83aaf"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703786519.pdf?alt=media&token=e7ff3834-be52-4410-b744-3713bfe73e98"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703809533.pdf?alt=media&token=b6641ab3-6015-4b23-88c9-85eeb0be0ca0"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703865037.pdf?alt=media&token=431ba9cc-357e-47ce-9021-47f75b1fa44c"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703865037.pdf?alt=media&token=431ba9cc-357e-47ce-9021-47f75b1fa44c"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703909239.pdf?alt=media&token=c67106fc-9427-46c1-913e-b3dc7db54c88"));
        userlist.add(new model("Indira College of Engineering & Management, Pune",6179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703931058.pdf?alt=media&token=299e411f-e59f-446c-8ea4-cb2e11ca8d74"));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703952286.pdf?alt=media&token=766f6026-fbb9-4126-95fe-30bad89dcc96"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703973132.pdf?alt=media&token=66aefbc0-257f-44ab-8981-927684fc8a29"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657703998221.pdf?alt=media&token=5e35a0db-df10-40c7-9f08-37440fbde8d5"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704021095.pdf?alt=media&token=cae1a222-766a-4d0f-93df-557142a79210"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704044407.pdf?alt=media&token=3148b622-ea2d-40ed-a3d0-2d4077eb1048"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704075125.pdf?alt=media&token=2c276b1b-51cc-40ed-a71c-8983682165f0"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704110306.pdf?alt=media&token=139b62ae-6ba9-4ebf-95f4-264791050676"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704294320.pdf?alt=media&token=6d42642d-1df7-4637-8f18-790f213c2f7a"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704325656.pdf?alt=media&token=9f719a22-7803-4aa9-92db-d141ee9b6127"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704350099.pdf?alt=media&token=6dc12d83-27cf-4149-ac99-ddea39c7825a"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704381526.pdf?alt=media&token=64956b5f-b57c-487f-9dc9-5e46ba7a8a75"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704407472.pdf?alt=media&token=a83391c3-c953-47e0-8a4f-38ad5a76418b"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704431870.pdf?alt=media&token=22e7ab7f-b09c-4594-b39c-88b05d35f48a"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704462262.pdf?alt=media&token=d6353bee-e39a-484e-bc4e-7da75215fe2b"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704490997.pdf?alt=media&token=b3ab0ebb-43cb-4470-a9ea-e2f7c2b741df"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704514334.pdf?alt=media&token=3e725c87-c41a-4a8a-8307-ac216f9d34e6"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704534529.pdf?alt=media&token=4d5b4bc5-dab8-468c-b6bb-423f61eb3754"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704557788.pdf?alt=media&token=53ab605c-50fa-404f-b7c5-0f575401512f"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704580115.pdf?alt=media&token=bf73697d-4ce5-4d0c-8850-909b37784a24"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704606030.pdf?alt=media&token=c7b7ed51-e9ce-407f-9371-29b352c59675"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704629228.pdf?alt=media&token=bcab76c7-628a-4e9f-87a1-4c4272b70b61"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704655445.pdf?alt=media&token=b1dd024c-7166-421a-ad05-240b249238e4"));;
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704727107.pdf?alt=media&token=db467225-d6bc-41ee-9762-2d64af467d61"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704748246.pdf?alt=media&token=01ada3e3-d1f4-47d6-a97c-960f1ac9433a"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704771442.pdf?alt=media&token=25da5a70-41b8-430f-8632-f96e5df5295d"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704801620.pdf?alt=media&token=47878d39-0d63-4c5a-8cec-275fcfc1b746"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704829178.pdf?alt=media&token=7de82564-45b1-45b1-9dde-c28f50fc851d"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704874616.pdf?alt=media&token=a8bcbf19-084a-417b-8795-b00c17e3a08f"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704898354.pdf?alt=media&token=5451f1f8-de7d-453a-aef2-859ba1d3eb30"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704924484.pdf?alt=media&token=2960f76d-434c-4478-855a-ea54a8259c27"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704950509.pdf?alt=media&token=5fa9b689-9ce8-4e39-9171-284dba5d7d67"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704976798.pdf?alt=media&token=1e443571-f7f7-4a45-a5df-a65c1ef4c67f"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657704995320.pdf?alt=media&token=ab7b7344-51d8-4a0a-bd4d-42de35e8ad22"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705024520.pdf?alt=media&token=f09acfa1-cb25-4969-8f6a-094bf78c8fad"));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705064713.pdf?alt=media&token=832b8d11-a08b-4173-8ba1-8c36fd5b51eb"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705103545.pdf?alt=media&token=89645043-e95a-4a77-8054-9355666d3269"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705129565.pdf?alt=media&token=fabf748f-0828-438f-86ea-d4306a512798"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705152537.pdf?alt=media&token=9787e98d-8b76-4bbc-93ab-060f3efdc1e5"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705173676.pdf?alt=media&token=25b2a9bd-49b2-4620-b3b7-f127e06a7599"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705200078.pdf?alt=media&token=ebf1a806-aa58-4f09-a801-60baaae5031f"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705243689.pdf?alt=media&token=5588ceba-ae71-4def-8fff-2bd291bc1498"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705269621.pdf?alt=media&token=73a8b746-70d5-45a0-bc21-29e8038d9297"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705294006.pdf?alt=media&token=0b49ca21-007f-4909-876d-b7cdd421978f"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705319525.pdf?alt=media&token=e6c27bd7-d129-4088-a19f-bdcd04eb0930"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705344569.pdf?alt=media&token=d2bdf921-95cd-4d77-9dc2-ad8b394f7e40"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705366179.pdf?alt=media&token=2845891c-8f52-4c2d-b58f-115241ee2e6e"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705385953.pdf?alt=media&token=0d18a365-2045-4b23-b0cb-09021a64c60f"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705409385.pdf?alt=media&token=56db2773-a717-4c6f-bd12-05c7065ad219"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705431550.pdf?alt=media&token=32fb7647-3b88-436d-870b-8e15fc4a8c81"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705466714.pdf?alt=media&token=cfc2ab70-e4b8-49d8-9435-24b5fc2e7076"));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705517344.pdf?alt=media&token=8c84ee5e-b1f3-4cb3-b5f5-8f514a040b47"));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705597584.pdf?alt=media&token=3fee929e-39f6-485f-a078-be6431a590ea"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705636747.pdf?alt=media&token=735bf0ee-823e-4c9d-821d-a70e7995a00d"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705660302.pdf?alt=media&token=92851097-3295-4711-b21f-f0c9ccfdc4d9"));
        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705681851.pdf?alt=media&token=c5a402b0-2073-4227-80c4-dcf8f58fcdb6"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705704547.pdf?alt=media&token=74cb596c-2b14-4d4f-8181-4baee97b67bb"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705730029.pdf?alt=media&token=a91ae8ed-4b1a-4037-b624-d99e5161f65b"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705785567.pdf?alt=media&token=21d93851-ee23-43c5-bd8c-e0882f67767c"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705810806.pdf?alt=media&token=366fa25d-6808-4069-98a9-d30ac95d5e19"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705785567.pdf?alt=media&token=21d93851-ee23-43c5-bd8c-e0882f67767c"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705861046.pdf?alt=media&token=bda0d5b1-2c16-4c53-8dc2-b29339ce0bbb"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705910185.pdf?alt=media&token=fbb96aa7-270a-4c9c-a5e1-9a0383ce4f3d"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705931236.pdf?alt=media&token=db9d26f4-5b37-41e3-bf9b-ec6033877055"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705973397.pdf?alt=media&token=c3ef9d75-2b79-49f1-b98d-7a19623e99a1"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657705995127.pdf?alt=media&token=acf1e4fe-b3be-4f70-a7b9-0764c25dceaf"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706018100.pdf?alt=media&token=9b1f8385-8fb9-4040-87a8-5dca4e583961"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706044177.pdf?alt=media&token=e1dfb412-1c7a-4e66-abc4-9eb38886a918"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706077196.pdf?alt=media&token=36ce4424-d69c-403f-8c62-766a75366d75"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706106152.pdf?alt=media&token=d011d34b-b4a3-42e1-b815-555bbc938a5e"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706132660.pdf?alt=media&token=e16665db-94ca-41cd-8591-dd82bf402721"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706156571.pdf?alt=media&token=07f1a272-141b-4b04-bd65-2cb39bb7815f"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706176216.pdf?alt=media&token=c675ef7b-1a8a-4c6d-896b-959033563371"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706211256.pdf?alt=media&token=3970f21d-433a-4917-a0c6-9fdd188f5124"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706236356.pdf?alt=media&token=94c0835c-0092-4b74-80cb-97fd3e49e832"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706264372.pdf?alt=media&token=b006ee8e-a94e-42d9-8376-07166b8b84f1"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706287326.pdf?alt=media&token=a3bd344e-9165-4e9d-aa0c-12fa7c938dab"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706307129.pdf?alt=media&token=e8078641-16b5-4cfe-9c5d-834a9207ba22"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706335040.pdf?alt=media&token=9bcbf0a9-7185-464b-9d9c-16a72532367c"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706364537.pdf?alt=media&token=64d77d39-7e39-4085-aa15-7dbaa8ef0f76"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706392647.pdf?alt=media&token=a8ec5e10-a57f-43ef-a615-246ebd8088f3"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706540718.pdf?alt=media&token=71377fe4-4e14-44d1-82a8-1c956c82c29b"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706576984.pdf?alt=media&token=96aa66aa-9728-437a-8006-91a2af4c79e5"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706414982.pdf?alt=media&token=04fd8c9c-2b16-4a72-89eb-b4715420db58"));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706652386.pdf?alt=media&token=bdf08ea3-43f8-4852-b284-2a4ed021d3a3"));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706676628.pdf?alt=media&token=a6b26d44-cfd5-41a3-928a-65ddcb126f94"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706698133.pdf?alt=media&token=fb495e01-30c6-483e-9478-7c3c06c84612"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706719790.pdf?alt=media&token=f1009300-4fbb-4a5e-b183-286a355512f6"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706749505.pdf?alt=media&token=75bbf78a-b3a1-43c7-817f-c2bfffc79044"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706773311.pdf?alt=media&token=3c3999f8-3d62-48a1-80b6-4fd925793034"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706824902.pdf?alt=media&token=7a96a24c-d2c7-4c14-abcb-a6825cd997c2"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706851142.pdf?alt=media&token=5745bed9-863e-4441-8066-112b7f07266c"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706876005.pdf?alt=media&token=5cc50b43-5c1b-4db9-98b2-0c14f79f09b1"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706897185.pdf?alt=media&token=0057af6c-2028-4f96-b61f-a9948908567b"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706919168.pdf?alt=media&token=80018ee8-f26a-495e-8044-417d3dd5fd99"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6869,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706941394.pdf?alt=media&token=759a3c11-4fdc-421a-b865-0176f5f09462"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706963521.pdf?alt=media&token=db5a5bc8-0edf-4913-9229-1662ee783cee"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657706986804.pdf?alt=media&token=f3caa42e-fd3a-49a1-bb76-c1b3944c67c7"));
        progressDialog.dismiss();
    }


}
