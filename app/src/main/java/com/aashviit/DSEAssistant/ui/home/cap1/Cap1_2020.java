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

public class Cap1_2020 extends Fragment {
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


    private void initdata() {
        userlist=new ArrayList<model>();
        userlist.add(new model("pict",1234,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816267304.pdf?alt=media&token=1a83628c-af78-4c69-a01e-8a96c0f7d606"));
        userlist.add(new model("pict",1234,"https://media-exp1.licdn.com/dms/document/C561FAQFwscbpVms-Jw/feedshare-document-pdf-analyzed/0/1659067857433?e=1660176000&v=beta&t=zd2UHMiMllHZjnSjNezWsAxDIaWVVAUJ4JyBdrxid9A"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5010,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145800000.pdf?alt=media&token=f4087096-554c-4206-a465-c2a5c92d4232"));
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145551853.pdf?alt=media&token=4100a4e9-eacd-41a6-9e4a-245e480bc5a3"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145597214.pdf?alt=media&token=6cc2a6d3-1c61-4cfb-879f-c612c79dc051"));
        userlist.add(new model("Government Engineering College, Yavatmal",1012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145675340.pdf?alt=media&token=faa74705-812a-4708-aa6a-8c8eeba4d2aa"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145710030.pdf?alt=media&token=c727f2bd-43c9-473f-8507-a72738241835"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145733406.pdf?alt=media&token=ea1c20dd-ceaa-44e6-b8f3-ac6d1097ddca"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus), Amravat",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145753324.pdf?alt=media&token=fa95e21e-06ca-439b-bacc-102e6fb02585"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145776359.pdf?alt=media&token=7f4a2397-6f0b-4d4a-93e0-bc68cdc8df5a"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145800000.pdf?alt=media&token=f4087096-554c-4206-a465-c2a5c92d4232"));
        userlist.add(new model("Janata Shikshan Prasarak Mandalâ€™s Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145820120.pdf?alt=media&token=9c00c1c9-3f67-4e51-95cc-1fb37e61e7c0"));
        userlist.add(new model("Paramhansa Ramkrishna Maunibaba Shikshan Santha's , Anuradha Engineering College, Chikhali",1119,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145841897.pdf?alt=media&token=35806915-cf8a-4374-8de0-e22b40e103bb"));
        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145889358.pdf?alt=media&token=f0207e96-fb35-45b4-b114-e8e9f0ff9b65"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravat",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145867626.pdf?alt=media&token=02e92120-15ae-418e-a731-bf1d1cb86947"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145915640.pdf?alt=media&token=fe0d16cd-4d07-4f54-bc86-fcc285421336"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145969824.pdf?alt=media&token=e0156d7a-cd55-4300-9786-ca2eb8644505"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146021718.pdf?alt=media&token=5571df9e-9d21-41bb-8583-cad7dc129eb2"));
        userlist.add(new model("P. R. Pote Patil Institute of Engineering & Research, At Kathora, Amravati",1148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146116564.pdf?alt=media&token=e4526762-bc4a-4367-b394-c2525b29d231"));
        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146139261.pdf?alt=media&token=94befed7-2252-4214-82b9-a789f68bfccd"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146254057.pdf?alt=media&token=7fc8c6df-1408-4499-a405-531eb71ddbd1"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146294056.pdf?alt=media&token=354f4c94-c0b4-44c3-b664-43affc0b4d89"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146319740.pdf?alt=media&token=3e469e5a-d46b-4b8a-b247-33f48fb2d906"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146348398.pdf?alt=media&token=64085967-c7f7-4683-bcab-6eecf233e0e2"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146395775.pdf?alt=media&token=3e8bbab4-4913-45a5-b7c5-2cb2435aef03"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146421744.pdf?alt=media&token=e9cf064a-d5c3-4558-a834-809d2b98bd9d"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146446527.pdf?alt=media&token=67d801fb-2c2f-4155-9c9e-a53bc7141a03"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146467235.pdf?alt=media&token=fc0d54ab-8168-49ff-8b1d-7818767e4c0f"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146486693.pdf?alt=media&token=940a6683-abb6-4918-9e2c-2418ae61c2f3"));
        userlist.add(new model("Maharashtra College of Engineering, Nilanga",2128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146508211.pdf?alt=media&token=cd27f691-a9bf-42b4-8be7-791510038a6a"));
        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146533826.pdf?alt=media&token=c9a5e4ce-8829-478b-8d52-27e8cea9e25b"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146554430.pdf?alt=media&token=45b04e14-294a-42d8-a818-8a2c1892dc46"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146574600.pdf?alt=media&token=054461c3-efc1-4196-a3a1-e2aa2d0c717c"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146593261.pdf?alt=media&token=c01ed2ec-3987-4035-b56a-4072e16d7e42"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146637933.pdf?alt=media&token=7d8f4c80-e3eb-4981-8c07-73e549aca3a3"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146660342.pdf?alt=media&token=96300b44-6692-4aeb-b2b6-94a363638286"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146743618.pdf?alt=media&token=e31a1be9-bdf3-4ab8-a3c5-3ebfdaa9177c"));
        userlist.add(new model("Adarsh Shikshan Prasarak Mandal's K. T. Patil College of Engineering and Technology, Osmanabad",2146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146765111.pdf?alt=media&token=7e166a04-2bd2-4606-85fd-cdd673152f63"));
        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146789220.pdf?alt=media&token=e6d88b34-2d4d-4155-abb8-fb5d5de1bfa6"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146808154.pdf?alt=media&token=a36a7977-fa24-4cd6-9596-1c6c4d2e367a"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146830581.pdf?alt=media&token=2cd6d48d-2e0b-46ca-848f-058b6fa31d9e"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146860259.pdf?alt=media&token=222ea793-f15a-4ac5-a246-33ddeb6b6e90"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146884997.pdf?alt=media&token=0328081c-1195-49b9-95b9-95d7f4d5d426"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146905834.pdf?alt=media&token=db8238d8-1186-47d7-9234-452e15a8a052"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146927184.pdf?alt=media&token=6338f5b0-cd9d-40d1-b397-d1fc0f6b4d67"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658146956620.pdf?alt=media&token=44df3c56-4165-4192-b254-ca52603a1324"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147008766.pdf?alt=media&token=e12be1b3-0090-4cc9-a8e7-cf5833b964c8"));

        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147029821.pdf?alt=media&token=29515154-83b5-43bd-b508-c1a7017af12a"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147053142.pdf?alt=media&token=5bbd6699-b996-42f6-a855-67446423c2ee"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147072301.pdf?alt=media&token=dae8e188-fafc-4664-a150-9608583f727d"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147095716.pdf?alt=media&token=3de3df60-8808-4e74-8340-396ef1ca8a17"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147120244.pdf?alt=media&token=2d22872b-adc1-4dde-8f05-ed05823d815a"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147141509.pdf?alt=media&token=bd70e13c-4627-4409-91f2-68cc7dd74cac"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147161748.pdf?alt=media&token=26f01338-9419-4694-887b-f38c91511f4f"));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147194262.pdf?alt=media&token=ede83467-f20d-442e-8393-5eecc1d832d3"));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147225287.pdf?alt=media&token=751657da-e9af-49a9-a39c-10d3f081f1ee"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147250915.pdf?alt=media&token=600ce2e2-f272-4840-89b4-7aa29bd04bbf"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147273539.pdf?alt=media&token=95e49ad2-28ec-41d0-9810-5f7e7c401ccf"));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147295263.pdf?alt=media&token=a8c4c9d5-4af2-4c77-af11-905d2949012e"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147315368.pdf?alt=media&token=b216a552-2c06-4d7e-84c1-1be768e4abf5"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147344872.pdf?alt=media&token=59e1b76f-933e-4ed2-b948-a0f8742ff88a"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147368209.pdf?alt=media&token=767e11e8-30a0-43b2-86ca-1d18c6fad13c"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147389904.pdf?alt=media&token=5a8d13a4-7fe7-4587-adbc-236046b25bd8"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147413044.pdf?alt=media&token=56962a23-c732-49ce-b2bc-692272de1ce1"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147443005.pdf?alt=media&token=d282db15-371e-4b8d-b4fc-4ceca7a42493"));

        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147464496.pdf?alt=media&token=0848de7e-b6f8-4c1d-b9a7-7f712ae686b8"));

        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147490201.pdf?alt=media&token=c94d5cdb-b812-419a-9801-a494bb11bb29"));

        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658147519229.pdf?alt=media&token=7398d9ab-a43e-41fb-b3af-e64dbbcb89e0"));

        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951670756.pdf?alt=media&token=a84d3c3f-60d3-471f-a873-fee8c6d7e5bb"));



        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951716139.pdf?alt=media&token=52ee7379-a857-4ac9-9de2-4ca300b6ffa6"));


        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951755860.pdf?alt=media&token=f415313b-16cd-4615-a977-6ffa7eee98a8"));



        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951789775.pdf?alt=media&token=01994433-ca83-4bad-9a3f-57a237a5fdfa"));

        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951826979.pdf?alt=media&token=7a52cfe3-35d6-45ca-835c-09a077b3ddba"));

        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951868412.pdf?alt=media&token=4cacd028-1cf4-46d7-9c78-607625a6f69f"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,""));


        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951938566.pdf?alt=media&token=f2c6c995-17bb-4c33-9fdf-b346e4ab67b6"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657951979357.pdf?alt=media&token=56d46e96-d44d-45c8-b793-d66e32ca13d3"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952017704.pdf?alt=media&token=1f92b81a-1ebd-4140-a3aa-646e3994e725"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952050360.pdf?alt=media&token=99b21bc9-79fd-48ab-8306-a2df9150327f"));

        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952087290.pdf?alt=media&token=6f685ac5-11eb-416e-9a8a-bd79679eb54d"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952277377.pdf?alt=media&token=c4b97311-000c-4c2a-9790-9fa7643930b8"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952317891.pdf?alt=media&token=8c453262-368b-4b34-9fc7-b8327c9dafab"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,""));


        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952450783.pdf?alt=media&token=9d6290c9-9199-4a54-823c-929bdf715d19"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952590455.pdf?alt=media&token=93e37710-427a-40b8-bddc-e344f19ac1c0"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952633170.pdf?alt=media&token=6c910231-a1df-4211-be27-7b5effffebc4"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952699079.pdf?alt=media&token=49573689-dce1-4354-bf63-7fdcadad479c"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering and Management, Nasarapur, Chandai, Karjat",3220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657952740487.pdf?alt=media&token=dd953c94-469e-48a0-8174-23c24988cf39"));

        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,""));

        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044172155.pdf?alt=media&token=0bd7500e-52bf-4f94-95d8-9cd9909c13a5"));

        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044211414.pdf?alt=media&token=72343367-5013-4383-bd16-7dd9babf0b8a"));

        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044250179.pdf?alt=media&token=a260a4b9-f49f-4b6a-ac4d-943a4263df08"));

        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044291363.pdf?alt=media&token=ecc11df2-bade-49d2-a6b6-fa6b9753afa4"));

        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044329029.pdf?alt=media&token=8d4e2718-eade-40d1-94d5-150616031559"));

        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044370321.pdf?alt=media&token=9cfd37f8-de3d-4527-a73c-b445d6a21eff"));

        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044402346.pdf?alt=media&token=a725b3fc-35cb-49d4-9f33-0103d6e043de"));

        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044473907.pdf?alt=media&token=009a7fb4-2eef-45b3-9fc1-7133b4acb08c"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044509683.pdf?alt=media&token=a2c0a44f-80fd-4cc4-9695-8977ec7dc13b"));

        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044549259.pdf?alt=media&token=c23ef3e8-ff6f-4e31-bb90-0d8e6efd3cb0"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044590497.pdf?alt=media&token=f0c2b0d3-c8fc-4064-882d-4c5924aaefa0"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044631322.pdf?alt=media&token=1e21a55d-0235-43b9-b30c-88c9d29f855f"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044669472.pdf?alt=media&token=cce233b2-9758-4272-ae4e-f718317eca26"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044707068.pdf?alt=media&token=ff262bb5-f109-4eda-b8ba-30214c929037"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044745230.pdf?alt=media&token=b271c29f-8983-44a2-84f9-9d078011cfcc"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044783589.pdf?alt=media&token=136b4c8f-4ff4-4889-bdc8-c02706f27850"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044817966.pdf?alt=media&token=df3a16ec-f97e-4327-9254-c260a253ef9c"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044849767.pdf?alt=media&token=d17bb5b7-46a3-484a-933d-65e486784fc3"));

        userlist.add(new model("Indala College Of Engineering, Bapsai Tal.Kalyan",3503,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044884337.pdf?alt=media&token=a2d944f3-f53a-4def-9dcd-cab94d941dcf"));
        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044920464.pdf?alt=media&token=bfeb37aa-8ed1-4537-82dd-2966334d5cd0"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044953764.pdf?alt=media&token=d556517f-bfbb-4060-85f9-c5d70996bdbe"));

        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658044990191.pdf?alt=media&token=a4ba4b94-7edc-4fb7-8042-e9fc85f8499d"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045033706.pdf?alt=media&token=8c045e6f-5e79-48f1-8624-a63c217d851e"));

        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045125393.pdf?alt=media&token=8d46fac4-547e-40f9-b817-6b077bcac04b"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045166143.pdf?alt=media&token=2a493cc0-5cd9-4958-8abc-bd2c13a5d885"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045197245.pdf?alt=media&token=bcae90a0-a427-466d-bd8d-91525bb54e3c"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045234545.pdf?alt=media&token=76cc5690-1a31-429d-90d1-b6cd620b0570"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045272937.pdf?alt=media&token=fab8e785-5048-423e-a365-cf80ab278ace"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045320273.pdf?alt=media&token=21482c77-a312-4835-91fe-0c42aa7d431a"));

        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045357407.pdf?alt=media&token=c9fae329-4425-460e-b2de-0bd760cf705f"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045397920.pdf?alt=media&token=03416d4a-2acb-4214-acfe-058349ebe301"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045436218.pdf?alt=media&token=642809e6-dcf2-44eb-b2b8-a37aab50112d"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045468285.pdf?alt=media&token=c717a16e-3ccc-4e29-be7f-93b1bd5a70dc"));

        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045499049.pdf?alt=media&token=3d89ffe5-880a-41fc-9f51-87d5983e6cd9"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045610638.pdf?alt=media&token=b5d15e72-ba88-4772-bdcc-e5a87a974acc"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045646639.pdf?alt=media&token=f37b3f18-0dbd-48a6-919c-a98c7e2ce5d7"));

        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045680165.pdf?alt=media&token=20b3faf6-5fec-4c36-a8fb-614e36b0bb74"));

        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045721027.pdf?alt=media&token=78206140-2191-432d-b6e2-8270f2f0afa8"));

        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045760819.pdf?alt=media&token=e3128b28-ccb1-4856-911d-481e1355d1c5"));

        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045795653.pdf?alt=media&token=dda47923-4344-4716-9969-09c93f601e66"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045827389.pdf?alt=media&token=c237b0fd-70fd-4e29-8c5d-4dd8e6566b29"));

        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045862990.pdf?alt=media&token=af5b9af9-c90c-49f4-a3b6-1ebc41893a9f"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045907103.pdf?alt=media&token=5c0d325c-8b05-4ca5-9871-bb205080f8dd"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658045943596.pdf?alt=media&token=ba726403-32bc-40f8-88c0-0df324212253"));

        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"Anjuman College of Engineering & Technology, Nagpur"));

        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046033959.pdf?alt=media&token=1c297989-c921-48ae-bd92-7f7f24254d9f"));

        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046073365.pdf?alt=media&token=880f4365-d811-446e-bfa2-30f911ecb2ff"));

        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046206953.pdf?alt=media&token=e5f46478-cf3d-406d-a9d9-d9055de94a4c"));

        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046263041.pdf?alt=media&token=a241bc67-40e3-47eb-953d-9c6aee9c1911"));

        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046297827.pdf?alt=media&token=e6e1619a-9b31-45d9-beee-486d6c88a5b0"));

        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046335107.pdf?alt=media&token=cd62cd1d-ffb2-458d-a623-853abe90d6eb"));

        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046363079.pdf?alt=media&token=9cd6c05c-b5b6-4889-9255-68c1d65c9b2b"));

        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046400775.pdf?alt=media&token=5b199d83-ea7c-4916-b80b-815d43f0a9bf"));

        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046436024.pdf?alt=media&token=b6aa7898-71e8-4aa7-987c-c4061cb4ccdf"));

        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046483846.pdf?alt=media&token=7def8a53-8235-4360-af44-be629a912ad2"));

        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046521486.pdf?alt=media&token=b5e7ab96-f9c4-4089-96a3-8e32eacf9480"));

        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046555154.pdf?alt=media&token=8c57ae99-a51b-464e-9402-aef58753412c"));

        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046595992.pdf?alt=media&token=daec8623-0a46-4683-894e-07974869cf65"));

        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046716336.pdf?alt=media&token=384571fe-dde9-4456-9b9a-09aa65373bb3"));

        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046758314.pdf?alt=media&token=187bbc32-7ba9-441a-bc49-376d8d2dc88f"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046828685.pdf?alt=media&token=0f2190c1-6efb-43e1-b62a-c018fa1392fb"));

        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658046858455.pdf?alt=media&token=2dc190b0-86c0-4936-8b58-c200551980f7"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048604237.pdf?alt=media&token=53f02dc3-387f-4bcd-8a15-888591bd3d3e"));



        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048642934.pdf?alt=media&token=4fbd51b3-aa6f-40ec-a47d-e634a45effbf"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048697392.pdf?alt=media&token=bb191070-fed5-47c5-82a1-52633fe89e8e"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048738336.pdf?alt=media&token=cfcbfa07-8710-4eb1-a97e-2cea39dd9271"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658048779609.pdf?alt=media&token=8dcc3214-cc2e-49ed-83d1-73261248d0fb"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050224440.pdf?alt=media&token=2994cddb-8f25-4752-9fbe-4e269caccc55"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050260067.pdf?alt=media&token=a17be5a2-615d-4625-b963-8f3c92e348ee"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050300369.pdf?alt=media&token=d498fcef-ba6d-4979-8add-ccbcdabc3598"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050338451.pdf?alt=media&token=2f1be672-cffa-4c30-8521-59ca32312794"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050441132.pdf?alt=media&token=d4956271-5404-4a1f-8b16-b6936d7108e1"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050405868.pdf?alt=media&token=617894f5-4652-4c8c-a1f6-118820027511"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050487628.pdf?alt=media&token=4517ac3b-97a6-4010-8af6-b02c13c63ddd"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050521532.pdf?alt=media&token=32e04043-c78c-49df-8d91-38c70e84a2f2"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050557491.pdf?alt=media&token=714ac4fa-0a53-4a51-9394-881fd40fea19"));
        userlist.add(new model("G. H. Raisoni Institute of Business Management,Jalgaon",5152,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050602179.pdf?alt=media&token=b8ad7294-978b-4652-b412-4ae4f1aba650"));
        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658050639060.pdf?alt=media&token=c81eb023-0f38-4a1e-8331-33d620923472"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134847884.pdf?alt=media&token=2012d6c4-b6d5-4283-a5ce-659ada546b0f"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134890194.pdf?alt=media&token=557796e7-d2d5-4f13-95f8-97b6ed62334c"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658134930978.pdf?alt=media&token=2c27f393-80ac-49f9-a6a2-cfaf30130b6e"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135017987.pdf?alt=media&token=adb228b8-f868-48a7-9b95-3dffac6aba05"));

        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135063372.pdf?alt=media&token=ce38a8bc-2df4-44bb-bdb0-26e334453ea6"));

        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135096500.pdf?alt=media&token=b9492662-b9e0-410c-9309-0090d55adbd9"));

        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135133114.pdf?alt=media&token=571e3b3d-f0f2-4790-b1c2-c0473468b610"));

        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135241329.pdf?alt=media&token=5d70f801-386e-418b-a0ca-710ce3e0a285"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135202775.pdf?alt=media&token=da764b9b-1466-44ff-a9b6-b8044b786a65"));

        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135285831.pdf?alt=media&token=0e67ee8d-4264-415e-88ff-ffd94ada499b"));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135322385.pdf?alt=media&token=6a25311f-5786-4460-a3e8-8f03456cc746"));

        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135357841.pdf?alt=media&token=b534555a-7755-40c6-ad03-49942d6251e2"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135397266.pdf?alt=media&token=80f47028-511d-4c7e-9d7e-bec338e9a8d9"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135432786.pdf?alt=media&token=9fea74f5-1d16-4b70-a1c9-1f88bb3d3992"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135464411.pdf?alt=media&token=e4cdefef-8c4b-4191-8802-74a169b73574"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135502172.pdf?alt=media&token=94890fd6-05ba-4519-94df-c8979b6ed4fc"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135548848.pdf?alt=media&token=06bad791-6aaf-4073-95d9-6f9f22cd5062"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135587928.pdf?alt=media&token=006c2529-5183-4264-a21d-f41f41664cbd"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135674252.pdf?alt=media&token=7737bc4c-0fcc-4376-a3d4-91b92abd3b65"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135631676.pdf?alt=media&token=baa295b4-7d3a-475f-9ecc-3a468d95bd3a"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658135706459.pdf?alt=media&token=3c81923f-153e-46c0-884f-b709e2681f64"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136028815.pdf?alt=media&token=b961f36c-9527-454b-a60d-0f72ec032513"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136079705.pdf?alt=media&token=5ba968bf-ce81-413c-ae3e-c395903cd1f6"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136115586.pdf?alt=media&token=4a527446-7a80-4a7c-9218-f02dbdd3563a"));

        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136149822.pdf?alt=media&token=297a077d-9f8a-433c-9ec6-ccd4abf0d14f"));

        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136192753.pdf?alt=media&token=c7175590-74d7-4125-9ade-57c69bc38394"));

        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136233525.pdf?alt=media&token=608c28b9-c382-479a-accb-c79e70b78f22"));

        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136270525.pdf?alt=media&token=cb3e7f63-f487-44c1-a59a-a9862639654f"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136311873.pdf?alt=media&token=c5b291a6-0f74-4b35-aa24-ab9066a71ef2"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136334507.pdf?alt=media&token=c120fec8-3fab-4f20-ba5d-a6d81f877f2e"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136369890.pdf?alt=media&token=dcec4796-a6fe-4b37-ad3f-813811970dac"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136461907.pdf?alt=media&token=3240bfc9-ce8e-47b5-9846-983e02e98a0b"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136500338.pdf?alt=media&token=824602ae-90f7-47bc-950c-e46642ff0d1b"));

        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136534920.pdf?alt=media&token=72c8bedc-bf94-43df-80dd-f4e3250b08df"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136566505.pdf?alt=media&token=de4de22f-ce28-4949-b015-1b123fefae67"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136624545.pdf?alt=media&token=129e4117-fa3a-4674-b75a-e73a4ec681ca"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136656724.pdf?alt=media&token=92ee56cd-249c-4a28-97e0-52f7feb54e46"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136690427.pdf?alt=media&token=8ce6a5da-2248-47c2-9ff9-0778c78bba24"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136724579.pdf?alt=media&token=dcf70b5b-e50f-4939-b9de-d20d831969cb"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136765309.pdf?alt=media&token=d4785c37-c7bc-4952-b011-ea0967b51047"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136804944.pdf?alt=media&token=2e23fbac-caba-4afd-959a-6077915b2196"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136873605.pdf?alt=media&token=c300696f-309a-46e9-aaf4-585e3ac9590f"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136910511.pdf?alt=media&token=84ec2aea-165f-433d-8f0b-2453d9e737cb"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136940775.pdf?alt=media&token=4a4b6609-d7d4-4e1a-a212-6a5fe9b309a4"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658136978004.pdf?alt=media&token=36e71fe9-a3da-4f62-9920-09d71662f5af"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137016394.pdf?alt=media&token=1c7c25ea-c0ac-4e55-841a-b4f0a29b7841"));

        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137064790.pdf?alt=media&token=f9a4861d-b081-42af-a888-edc1a0e28065"));

        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137100438.pdf?alt=media&token=36a5738a-0e90-4b87-9b2a-c6ddbe9c7929"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137134858.pdf?alt=media&token=3c77614e-3135-41ae-a3dd-df1ddf1851be"));
        userlist.add(new model("Indira College of Engineering & Management, Pune",6179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137181344.pdf?alt=media&token=d35c5cae-01c6-4792-9e18-7688b8106150"));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137214839.pdf?alt=media&token=d99eb660-6aba-4e7f-83aa-1eae34050830"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137252913.pdf?alt=media&token=f8895203-902e-44a9-bebd-2228d99500f1"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137289022.pdf?alt=media&token=23cd10ba-a432-4a78-866f-65983706c4b0"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137327491.pdf?alt=media&token=ab1f33ce-871a-49e9-9299-59f9a761e6db"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658137360042.pdf?alt=media&token=9b04d556-f573-4be3-91f8-e8989c0dafbf"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143381462.pdf?alt=media&token=bf2615c0-3de9-4591-b696-67df3958dc90"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143445187.pdf?alt=media&token=72d9e1e1-6eb6-4ee3-a7de-4355df91ed73"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143481258.pdf?alt=media&token=179914a9-7022-42cd-b6c8-cfb396d26d06"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143526331.pdf?alt=media&token=f0ca38e3-0480-4031-8432-83e5704499d3"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143562273.pdf?alt=media&token=3ca6dae3-9f09-4456-8869-1883649e69cd"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143607068.pdf?alt=media&token=37e88af7-d529-42c1-bd12-3fa7ec327137"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143642536.pdf?alt=media&token=b9d045fb-da88-4f42-9948-46e72036fb98"));

        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143677112.pdf?alt=media&token=2112a1f3-002f-44f9-a3fe-c7dee7d1a076"));

        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143722796.pdf?alt=media&token=d05fd431-f3fe-4182-9a71-6da9e9781997"));


        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143762581.pdf?alt=media&token=49561596-5713-4ba4-8d88-a097e5f6f18e"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143802892.pdf?alt=media&token=1b8bf2dc-f638-45be-b543-42277e70c3a1"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143835209.pdf?alt=media&token=c654e12c-9fd3-43a4-aa6d-89485648ad53"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143899883.pdf?alt=media&token=736ad647-5550-4cdb-be83-97676b87ff13"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658143960218.pdf?alt=media&token=523a81fe-c04e-462d-add9-c630d83bef6b"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144003597.pdf?alt=media&token=c5a434ce-a85a-4cf3-94b0-d5319ca02faa"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144037658.pdf?alt=media&token=57e5c40d-329c-476c-9a3e-87f063c4446f"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144127715.pdf?alt=media&token=7e2133a2-890e-4092-9397-3dde995b187a"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144202308.pdf?alt=media&token=0b7db794-db60-4477-8daf-f4c36205b5a0"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144239850.pdf?alt=media&token=6b8f1744-a3ce-4017-b2dc-e37bbfe3aef1"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144275413.pdf?alt=media&token=76c1f660-802f-4928-bfee-96b4f93c7045"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144311582.pdf?alt=media&token=1f6934e3-35af-45c3-bf10-37155ed39ed1"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144349934.pdf?alt=media&token=ec7b61f9-4950-43bf-b337-ae2c18d20054"));


        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144391474.pdf?alt=media&token=b0696fdd-1caf-4dc8-bffb-6f84e58b9ca3"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144427736.pdf?alt=media&token=612ae544-9da5-4a22-b552-b3b56fd16b42"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144459942.pdf?alt=media&token=8bcef00c-24eb-433d-bffd-ebdf5fb782bf"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144504747.pdf?alt=media&token=d6bd6ff8-c1a9-469a-a341-507b1dcebbff"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144540856.pdf?alt=media&token=c3121be7-5427-460c-b5ea-1e5bd53083b4"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144576142.pdf?alt=media&token=e43bef12-8947-4775-9445-068f01f6cf42"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144608972.pdf?alt=media&token=0444e830-53f2-4de2-9882-b28735d51abb"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,""));

        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144704800.pdf?alt=media&token=3f48af46-68e0-4797-b409-4b6142403c61"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144740389.pdf?alt=media&token=f3ec50a7-f23f-44ab-8b3e-7871c1e63538"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144786231.pdf?alt=media&token=e8353535-1c89-48ee-9359-6ce539a9e035"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144823114.pdf?alt=media&token=4eb87969-3f02-4201-8ac7-989b5ce445ce"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144854943.pdf?alt=media&token=4a1c5c17-1593-495d-ae07-52f388b67b6f"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144888841.pdf?alt=media&token=2a1c0eb9-be6d-46a4-bfad-be9b5a34d2dc"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144934908.pdf?alt=media&token=a4cc18fc-8148-47e5-8abc-ade1130fdd49"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658144969538.pdf?alt=media&token=ec102e6d-9608-4a2b-88d5-48900bc2779d"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145002055.pdf?alt=media&token=1cc725a9-d33e-4bbb-816d-0f5b85cdb206"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145035009.pdf?alt=media&token=a6dc9024-fe3c-4cbd-87f7-642434ada1b7"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145068224.pdf?alt=media&token=1a00e262-1dc9-4df5-aa1d-c92c7a10d763"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145110877.pdf?alt=media&token=16ec6048-d95b-4f68-9636-32ad5bb00808"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145142939.pdf?alt=media&token=cb54db8d-c56a-4950-adff-c11eccee0d76"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145177720.pdf?alt=media&token=20763988-8247-48a5-ab3f-8effceff1894"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145213978.pdf?alt=media&token=e70b4b14-0677-48ed-a0b5-67d24a903530"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145252952.pdf?alt=media&token=d973866b-7b1c-4c68-8fc8-fba312f787b5"));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145292486.pdf?alt=media&token=02a8b440-dde4-4dad-9881-237db65fa6f5"));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145314386.pdf?alt=media&token=13f05112-374d-42fc-986f-19dbb42d1831"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145346034.pdf?alt=media&token=0e710aca-2b28-4a7b-914b-975283cc247b"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658145379459.pdf?alt=media&token=1ae7d17e-cc57-4df4-ad6b-0ce0571020f1"));
        userlist.add(new model("Nutan College of Engineering and Research, Talegaon Dabhade Tal. Maval, Pune",6419,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156381021.pdf?alt=media&token=75ed3bfb-e6fd-426f-8ba1-69970060c143"));
        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156418067.pdf?alt=media&token=6c20111c-e7fb-45ef-b491-502e317535cf"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156459407.pdf?alt=media&token=103eeac3-46e8-4d1f-acad-e189fe11e0db"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156497202.pdf?alt=media&token=f080b141-c931-4418-b1f2-55c3fcadc3b3"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156536278.pdf?alt=media&token=6a59342c-3763-4879-8048-78bb75d06a9c"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156563680.pdf?alt=media&token=0c493bd9-5e1b-4234-bbfd-212f46e935ff"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156598163.pdf?alt=media&token=c8923a9d-e304-4a2c-a569-731a68bcdc41"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156630771.pdf?alt=media&token=bb013d31-d079-47c4-8b13-b53c611c09e9"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156743025.pdf?alt=media&token=509e6b68-cab4-4802-be9f-ae79351f0ba0"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156774690.pdf?alt=media&token=c68ba479-f079-4603-91a8-a67f3471e91b"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156811634.pdf?alt=media&token=c580f151-945a-4068-8dab-2b210906ebad"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156848353.pdf?alt=media&token=a8e171bb-21ba-4e47-9f01-49e31a97fe76"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156880576.pdf?alt=media&token=0bc73f92-96d0-431f-bc2f-c14a1e811fda"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156913789.pdf?alt=media&token=b54a58fc-89d3-4d4c-960a-44005d53d529"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156951770.pdf?alt=media&token=b89dbe82-7a65-4869-beff-ad73181b4589"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658156987379.pdf?alt=media&token=2b6e34cc-4550-4945-bb3f-707a7f2fc8e1"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157138754.pdf?alt=media&token=e2cadf38-7c9e-4581-9eec-430c05cacfa7"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157171583.pdf?alt=media&token=bee7160b-95b7-4890-9b23-cf0b9fdede90"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157206885.pdf?alt=media&token=48a0d8a8-db28-4c8d-bed3-8bbc77ca47df"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157238289.pdf?alt=media&token=8dd81392-bf26-4797-91bf-ba2fa3b3b6cf"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157272715.pdf?alt=media&token=e13fea4f-b9cb-4477-8e11-5250747c752a"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157307858.pdf?alt=media&token=1fc74d4f-6735-455f-9fe5-8eac784d5c58"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157350726.pdf?alt=media&token=68e263de-eaf5-4240-aa0e-a8e88133f5c9"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157381959.pdf?alt=media&token=364a1b7f-d603-48f5-b784-a1a1c7534295"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157415190.pdf?alt=media&token=3de5cf4d-850b-454c-bffe-8e0b736fdb86"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157450711.pdf?alt=media&token=701075a8-08d0-4ce6-9e08-89d4a8d85420"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157485443.pdf?alt=media&token=61802a8c-af4e-42b9-858c-de02c10ac6f4"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157520422.pdf?alt=media&token=d08379cd-3a12-4a8c-b3de-6e4f3036dd4d"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157556555.pdf?alt=media&token=67b48e07-33d8-4319-9153-fe968671cc45"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157604658.pdf?alt=media&token=1c1be8f1-c470-4750-9b28-b74ad4e4deef"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157639088.pdf?alt=media&token=1a1efc2b-e107-4d7b-818a-272472c56d15"));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157675981.pdf?alt=media&token=fa6a9347-fbd7-4dee-ac49-58d4fe09e8a2"));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157701309.pdf?alt=media&token=84b9118e-8e20-4c27-88d2-2421093afc7c"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157734349.pdf?alt=media&token=e28d9453-cf00-4ac3-bb21-8d4a64f14a20"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157767085.pdf?alt=media&token=fcb78de8-fe55-48a6-aeff-462e8f49ff10"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157800213.pdf?alt=media&token=f0fb250b-7ca1-47f0-8b01-f30c2723629f"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157842690.pdf?alt=media&token=419347c4-36f4-45f2-94a9-f19f8fdc126b"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157885557.pdf?alt=media&token=682fd9a0-8c05-4f76-9ceb-926aed1ad6fe"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157919538.pdf?alt=media&token=438a050b-df2a-4912-ba4d-412d3cad0d71"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157950616.pdf?alt=media&token=250582f7-f92c-4acb-b278-a8f4f815d7ae"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658157987398.pdf?alt=media&token=f25b0646-d6bd-4a62-a173-737786db2060"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158023239.pdf?alt=media&token=0629fa0e-e101-4d21-b22f-1b7563bcac8a"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6869,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158057393.pdf?alt=media&token=bd3a6aa2-23fa-40ae-a22f-682942d9896f"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158092469.pdf?alt=media&token=e691222d-59f3-4510-b3ab-1d1e1aab8b0e"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158127151.pdf?alt=media&token=b5c795e4-eb29-46e2-82e4-f72a84c70f80"));
        userlist.add(new model("Shree Siddheshwar Women's College Of Engineering Solapur.",6938,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1658158167479.pdf?alt=media&token=1dfb0954-7f56-42d2-a8a0-a3faae2c4a2b"));

















































        progressDialog.dismiss();
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
        db.collection("Cap12020").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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


}
