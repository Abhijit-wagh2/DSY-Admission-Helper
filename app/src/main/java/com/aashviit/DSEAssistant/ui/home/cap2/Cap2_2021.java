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

public class Cap2_2021 extends Fragment {
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
     //   EventChangeListener();
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
        db.collection("Cap22021").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785501187.pdf?alt=media&token=b0c4fc42-a133-4cdd-8422-c044cc257c2b"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785575639.pdf?alt=media&token=0be6b3eb-7518-4a9f-8183-cfbd1b08d9bd"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785646342.pdf?alt=media&token=4c8f2747-377d-41ee-8d1d-dd69c71dd8b2"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785709770.pdf?alt=media&token=268d6dc9-eae0-4974-861a-9749d7588643"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785843037.pdf?alt=media&token=86048238-27df-4c5a-ac17-9ca13b882215"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785912495.pdf?alt=media&token=31268078-6ec3-4bd4-872d-fd5e60573455"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657785978739.pdf?alt=media&token=2cf7ddd9-9c65-4dbe-a784-aafdbab0cc4e"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786052189.pdf?alt=media&token=2eebe23d-6265-4424-98f4-fcb171ebd827"));

        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786130926.pdf?alt=media&token=ce0b9a4c-1533-4273-8fdc-ad2bff40c2ad"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786203845.pdf?alt=media&token=1d877c5f-145f-4647-90b9-b5fa725e02bf"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786266422.pdf?alt=media&token=1fc69bf1-109f-410c-9e6c-7789133543d1"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786493975.pdf?alt=media&token=cfaab6bf-923e-416e-b806-08f6aded5d0e"));


        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657786852722.pdf?alt=media&token=523536da-03bf-4bad-8be3-737f1efd8307"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787263454.pdf?alt=media&token=8ab7487a-f8c0-4644-9a3b-90cebfa0b423"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787327106.pdf?alt=media&token=f46d23bf-39d9-41d0-b2bd-19020aa106e2"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787396604.pdf?alt=media&token=09b5fce2-6014-431b-8f71-1208c33136eb"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787462418.pdf?alt=media&token=2fe571c4-71c7-4fc0-a656-526491e1c77b"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787528212.pdf?alt=media&token=05e819a0-7b97-4260-955a-0e6e2162d3c6"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787603388.pdf?alt=media&token=e77bc033-bea4-43f0-9f7f-df7e97b15bd2"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787678557.pdf?alt=media&token=61870a5a-c845-4a5f-88d4-b7c16eb11659"));

        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657787874921.pdf?alt=media&token=eda1e4d2-7bb7-4583-8618-dc2f1058aa49"));

        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788007401.pdf?alt=media&token=aae090b8-1e0d-4568-b7e7-f35ae3ebd06a"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788076854.pdf?alt=media&token=dab7d800-b96c-4f7b-9874-59c1856dca35"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788143327.pdf?alt=media&token=c7dddead-8a42-4f0c-be33-a1d3dfc0026b"));

        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788334786.pdf?alt=media&token=a24ff2f1-3a3e-40bf-981f-d5b381cbf636"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788412098.pdf?alt=media&token=652b868a-af03-4ab1-b298-5299e99e88fb"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788698942.pdf?alt=media&token=435ca842-cbc5-48ee-9c80-9cbe7bbc1d7e"));

        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788800254.pdf?alt=media&token=f95e74fb-af6a-4af2-8d62-d5056361b39a"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657788936831.pdf?alt=media&token=c8a0c905-18f2-4c1c-85ed-928000a37281"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789006072.pdf?alt=media&token=d7523b0f-9474-4d84-8718-862b3b6c04fd"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789165689.pdf?alt=media&token=368c67cc-6b3a-418b-a7cc-2ad1c3395b61"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789229305.pdf?alt=media&token=1635ecd4-a016-40c5-a757-7a37ca5ffa09"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657789324757.pdf?alt=media&token=e4e4801e-50c6-4d2d-ad78-640e1de297af"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795671245.pdf?alt=media&token=82d5e8dc-f8d6-4da2-87f2-728130a76b2c"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795751760.pdf?alt=media&token=93f13cca-65ce-4927-92a9-a5a761103ae1"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795822653.pdf?alt=media&token=a41b6c46-36ee-4651-a596-3a8467a4bd8c"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795884297.pdf?alt=media&token=d547a55b-451b-4c85-8ded-dd5652eff6d5"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657795946496.pdf?alt=media&token=ec894ac0-b088-40f5-baa9-18d7c7e16f03"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796014445.pdf?alt=media&token=e3d45aea-f368-4cc3-bd9f-c4ce7fb8b684"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796089226.pdf?alt=media&token=7b3c8e91-e737-470f-8d8f-1e5a56d4ede7"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796148843.pdf?alt=media&token=d911cd46-c60f-4a79-b2fc-e548e6d7b08f"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796270687.pdf?alt=media&token=8989c457-e8ae-4723-a7f6-027c3565b2d8"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796342061.pdf?alt=media&token=582ad708-3943-446d-a376-ae1407e2117d"));

        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796471944.pdf?alt=media&token=c548e239-c691-49b2-b364-5c7b1aa5cb5d"));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796549353.pdf?alt=media&token=1c28b6b0-cc57-4675-8794-600afa389b27"));

        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796678541.pdf?alt=media&token=bb2942a9-b258-490f-91b4-d0125e0b3c17"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796742954.pdf?alt=media&token=f0e63afe-1e10-4ab6-bb07-65b26a98c611"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796827024.pdf?alt=media&token=3ec93fa5-ad67-4427-a7e3-f6f6744d4e58"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796892008.pdf?alt=media&token=fdb02d27-81eb-400b-97fb-564a52729505"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657796989216.pdf?alt=media&token=13b8bebe-8468-4da9-8357-c322948655fb"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797048621.pdf?alt=media&token=7ebd5426-84c8-4721-ba4e-2b38aebf9f50"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797125469.pdf?alt=media&token=a8aa30fe-adac-45c5-86f8-adc2cf7bb3e2"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797190606.pdf?alt=media&token=b084b9e1-a1ab-43ff-8c7a-120bb85fd8d9"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797279239.pdf?alt=media&token=e663d9cd-676f-4dd3-8461-7596e22add44"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797343396.pdf?alt=media&token=d64c5e22-dafd-48d8-9d48-a8003417f929"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797415077.pdf?alt=media&token=ebdce68e-b046-447c-b1c2-4af2c0efc907"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797485026.pdf?alt=media&token=d83cc994-495d-41cc-9271-df30456b55b6"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797543435.pdf?alt=media&token=29f29b5a-f32f-4feb-b207-89349ba60715"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797607205.pdf?alt=media&token=73c46819-34fd-4054-8a99-efdb4050df15"));
        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797668764.pdf?alt=media&token=ee62dd2e-2621-4759-807e-98ae5a1b9bb7"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797799182.pdf?alt=media&token=40f550cc-1902-4a69-8e98-82e414f914ac"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797855523.pdf?alt=media&token=baaf76e5-d92b-4610-906e-3ff6bd444803"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797913766.pdf?alt=media&token=64d40978-1426-4306-a687-09575febccf7"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657797975668.pdf?alt=media&token=1bfbd6f1-6126-4590-9cb0-c227c69db5e6"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798034533.pdf?alt=media&token=a63e94ce-47cc-4b00-a434-e20d36e94101"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798099622.pdf?alt=media&token=6558fc46-639c-4c54-8cc8-4f12c726eb6d"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798221490.pdf?alt=media&token=6be55ebc-b75f-4224-bb07-e26558b7ae9a"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798283844.pdf?alt=media&token=e7c7fc6e-5f7b-4787-b0bc-3ab47fb597cc"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798345541.pdf?alt=media&token=c5fabcda-d3e9-4437-8b83-f452368b1e36"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657798407231.pdf?alt=media&token=d04d7f38-2bae-48b7-967a-9a40e76036e8"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799134788.pdf?alt=media&token=8c5b7365-66ff-4a48-9e6d-37315fef624c"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799306776.pdf?alt=media&token=a88da37e-34c0-42b3-a6f3-09b6c89ad001"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799371652.pdf?alt=media&token=97bfda19-2864-4fd4-a249-2ab861f10b9d"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799450308.pdf?alt=media&token=2d210c9e-8914-4911-b063-30b43d129f6d"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering and Management, Nasarapur, Chandai, Karjat",3220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799510748.pdf?alt=media&token=2ae50f6d-d79e-419b-be47-b283cab9a0a6"));
        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799605596.pdf?alt=media&token=f37da00a-cf17-448a-9560-4cd9285a8b29"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799705851.pdf?alt=media&token=68d8b0e7-9dcf-4b4e-85ce-3c8aca2d6bd1"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799788483.pdf?alt=media&token=b13312f8-0240-4c5c-b086-b4c87a8b6b33"));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657799848474.pdf?alt=media&token=8863eabe-4d3c-42de-a572-3205cc61b2d8"));
        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657800086928.pdf?alt=media&token=7da0d603-2981-43e7-8c6a-59abdd83ff33"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803503974.pdf?alt=media&token=0928becb-46ed-4b38-b852-040de13cd266"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803568412.pdf?alt=media&token=5065a2b3-bf8a-46ee-a8ef-bb0a34bd12e2"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803629072.pdf?alt=media&token=615e1fca-d923-4a7d-87e7-6665ee4ed570"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803693865.pdf?alt=media&token=6e14a76b-f101-4454-a80c-c3518fa7a51d"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803752137.pdf?alt=media&token=98d95857-2478-4003-9742-1faac15f639e"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803798005.pdf?alt=media&token=e3ae87e5-8eb5-4866-ab00-32273e078a99"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803853600.pdf?alt=media&token=e9091232-94c6-44aa-894a-4a2ba8a71af7"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803933415.pdf?alt=media&token=053bf356-7a36-4510-b79b-004587505577"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657803988635.pdf?alt=media&token=41a1779f-ba1f-42aa-99f5-20c04ab3725b"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804062543.pdf?alt=media&token=10bf8a6d-288c-43f4-a791-8fe6c2f175f6"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804122938.pdf?alt=media&token=158b97e5-af0a-4621-b02c-f8aeaa0246a9"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804197516.pdf?alt=media&token=78b5ba50-1428-4975-bebf-48e7536a4f2d"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804261377.pdf?alt=media&token=92600d4b-4ecb-47d5-99dd-08d45a4e07f5"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804320502.pdf?alt=media&token=06e105b1-4ee5-471a-ba03-e6fdf6924b5b"));

        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804386550.pdf?alt=media&token=bd72059f-cb39-4ce6-9d8e-fb17ef6ec9da"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804451769.pdf?alt=media&token=1eb13ea2-548f-49f3-8a75-184472a78847"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804519179.pdf?alt=media&token=39feeddf-fc2e-466c-b5ee-d3b175a18487"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804602070.pdf?alt=media&token=a88aacb5-6575-489b-bc69-f37a7d64724d"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804669675.pdf?alt=media&token=fe5fcd52-d5c6-4d8c-b09a-f6b7cfdd2410"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804735059.pdf?alt=media&token=d3efc7ae-e1f0-45d0-a0f3-e5751c483aa0"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804819872.pdf?alt=media&token=b4b0ce62-404c-4940-9211-c787201a32f2"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657804900179.pdf?alt=media&token=27c7adc6-b65c-4f36-9c5c-2f60b841815e"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805003976.pdf?alt=media&token=b96706c9-db85-47d7-81f7-ce2c08a32d02"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805065246.pdf?alt=media&token=e814b1d0-671d-467d-8dd6-9be32d239b46"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805128505.pdf?alt=media&token=4191955c-8ac2-42c5-ab0b-ad4437d90497"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805207537.pdf?alt=media&token=eed24583-649d-4559-ad45-c00fee785141"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805263121.pdf?alt=media&token=270f6a1f-0acd-4f33-861b-efed4d0b9427"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805319348.pdf?alt=media&token=ec553ac4-5f12-4c9f-a6d4-9e8588fa41c3"));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805384476.pdf?alt=media&token=b987c782-795d-4cba-b634-b1c6f02c1ef0"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805451273.pdf?alt=media&token=a0220563-81bf-43d8-83bf-cae6c8a9f398"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805512999.pdf?alt=media&token=660e6d0b-e94f-49b7-a826-5e2914cdfe70"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805572004.pdf?alt=media&token=982b2742-3510-4e5c-8d4b-8db4fc71bc20"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805643035.pdf?alt=media&token=915e2e87-ae16-4d27-ad3d-a3f3526d4f4e"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805701109.pdf?alt=media&token=92b31a30-dfe6-4448-bafb-183a16426d7e"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657805791499.pdf?alt=media&token=b5aef0df-7e16-4cf9-a883-68a01968ef2f"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865773088.pdf?alt=media&token=198d1a4c-9df0-4454-a413-42a1692a6c3b"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865840013.pdf?alt=media&token=ed186bf3-99e7-4d17-b142-67f8f567e438"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865928265.pdf?alt=media&token=d882c066-5c25-4abe-98a1-68a6b73f9d2e"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657865989435.pdf?alt=media&token=bde2d8a4-3af1-4cc5-87b7-71bc8fc88f42"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866042075.pdf?alt=media&token=113eadf5-fc40-47f6-9758-6d0d84345a0b"));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866152698.pdf?alt=media&token=4873bb66-f5d6-4c15-99f9-3905a66bbe5e"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657866224915.pdf?alt=media&token=50b1ac44-5db7-40a5-b156-4659cad2b4d7"));

        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870863072.pdf?alt=media&token=ebf3f10e-d8ec-4269-bd98-5e6cdb71810a"));
        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657870950192.pdf?alt=media&token=45499331-b563-4673-b8f3-a726bc22ed2a"));

        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871193622.pdf?alt=media&token=81c04226-8156-4e21-829b-407e6918bd78"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871260694.pdf?alt=media&token=5b22c702-eeed-40be-9739-84ff14f7dce0"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871315845.pdf?alt=media&token=ac618728-b5a7-4e86-a307-9acc962a89b9"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871383488.pdf?alt=media&token=a8a5a392-8860-4f00-8647-9171b4ed1832"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871453769.pdf?alt=media&token=0cdaf1c4-b84c-40a3-bf41-1d19c553db95"));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871498414.pdf?alt=media&token=a84863bd-c6b1-4bbd-bfe5-2d053327e27d"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871570442.pdf?alt=media&token=2ca5d1b5-a66a-4fc9-a6c2-d942823545f0"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871645068.pdf?alt=media&token=916c3f6e-d58c-4b44-bb1e-c7c2475cec8d"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657871737906.pdf?alt=media&token=ede1d3a3-9b14-46e2-86c3-4cb52b2f88af"));

        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872046655.pdf?alt=media&token=3b561b31-e462-4f84-9cc4-b979af5cf7a5"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872110653.pdf?alt=media&token=6292102b-caea-426a-9e1d-d7e8bb8c4923"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872179020.pdf?alt=media&token=6a41a45c-46b4-47e4-bec6-2d5393673a05"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872239978.pdf?alt=media&token=fc4d2428-887c-4236-90e1-e403569c76c3"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872367648.pdf?alt=media&token=3344ae7e-4ce7-484e-8998-a89c1f7672de"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872565047.pdf?alt=media&token=c9ba5501-54be-48fa-bc59-30a12c02b274"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872518174.pdf?alt=media&token=06684f7b-6eb8-4e8e-ba1e-0cd3d953736f"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872622482.pdf?alt=media&token=1782a85c-1a43-4b07-bd2a-9f02192afcab"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872684321.pdf?alt=media&token=1b7fc360-d60f-4029-8bb7-362e59f9a46a"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872751059.pdf?alt=media&token=a78db9f6-9644-47d7-a789-5a1656765c47"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872840230.pdf?alt=media&token=f7e02dac-0797-460a-9706-5ea8300d075a"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872931673.pdf?alt=media&token=ae17ae55-f27b-4e0e-8577-10b785e912f1"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657872990901.pdf?alt=media&token=f5722dfc-3424-4834-a829-8a08374b47ef"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873057734.pdf?alt=media&token=335374ba-cec2-4235-954a-7659df77d946"));

        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873114628.pdf?alt=media&token=f4858098-493a-4b5a-828c-5b11ff42fc32"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873169269.pdf?alt=media&token=b421cf54-741a-4eff-921d-b12b16921bbf"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873225848.pdf?alt=media&token=d76cef31-2626-4696-b5b1-f73f663012ae"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873280461.pdf?alt=media&token=c5057312-0875-4a86-aff4-6e45cb4c258b"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873346619.pdf?alt=media&token=96269c80-ddc1-4aa0-9f11-703065ba8810"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873400949.pdf?alt=media&token=ace8d2bb-ca6d-4570-be39-c7742008943c"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873465092.pdf?alt=media&token=6e6361ad-466c-4784-87c5-5b98fa499866"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873529759.pdf?alt=media&token=7a5d51cc-f94d-4393-a83b-14e8298e2b36"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873595307.pdf?alt=media&token=7ebcb362-422c-4f4b-8a18-d7456adddd06"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873664087.pdf?alt=media&token=d05f5653-01e5-4576-b429-d5f152aa6144"));

        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873765527.pdf?alt=media&token=197445ca-3933-4db9-9925-fb414b0777d1"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873827093.pdf?alt=media&token=e39d2370-bde5-4483-835d-e6bc31be6f44"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873888459.pdf?alt=media&token=db87853d-2f16-42f8-ab23-17d7bbc0364b"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657873949718.pdf?alt=media&token=431fc59e-fe61-44b6-856a-5bca7043e838"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880048558.pdf?alt=media&token=728121a5-560d-465f-a1b4-26c1dbe8ac4b"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880116718.pdf?alt=media&token=01c7dfb1-d43c-418a-b863-6e8207038543"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880193528.pdf?alt=media&token=a801dc23-f403-4bb9-a66e-b60f6bca1e4e"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880246851.pdf?alt=media&token=a8b1a180-021b-4368-aa06-b06710f06569"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880365694.pdf?alt=media&token=d57b84e4-2e14-4bfc-b20f-a656ebc9be0c"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,""));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880429641.pdf?alt=media&token=e8989b7b-4253-43fe-ae98-e68287fec791"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880482666.pdf?alt=media&token=32e35381-0f49-4dfa-944d-ff9fe499d3fe"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880540098.pdf?alt=media&token=5f7c9f30-b18c-4306-a696-66b1cbb3e598"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657880592769.pdf?alt=media&token=a9217041-e776-4a0b-a61f-1f29a77d39c4"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657881745295.pdf?alt=media&token=22f2ebc7-0e8f-4b09-9e95-9ff33de162a2"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657882863744.pdf?alt=media&token=f430a2bd-ce0d-4cde-91dc-19019db9d73f"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883008014.pdf?alt=media&token=d578098e-b589-4380-b877-593de720777a"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883070285.pdf?alt=media&token=c601667c-c8bf-49e3-85a3-a0fe0b1849c6"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883198206.pdf?alt=media&token=cbcdfeb3-5f26-4f67-ba0c-0c445917584b"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883273184.pdf?alt=media&token=27bb58e3-8481-4de2-a654-269f4fe6bd42"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883357422.pdf?alt=media&token=1ca34527-e4ae-4626-8570-7cf92f6cccdc"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883476775.pdf?alt=media&token=ff50e564-ddf1-473e-beb0-79490bcfb472"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883534365.pdf?alt=media&token=0903f152-094e-44b4-aa29-e494a4d28bd1"));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883597891.pdf?alt=media&token=53b1c890-c4be-4ad4-a041-8100f55ede47"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883654305.pdf?alt=media&token=fb398cf8-55b0-41a0-8133-721f3cc52afa"));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883715388.pdf?alt=media&token=fbc4fa6f-97f5-4e7f-9d00-12bca0e7e3e3"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657883782468.pdf?alt=media&token=bc60148e-2f68-4d42-811b-5ab92a8c7ddf"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884040382.pdf?alt=media&token=318fd2e6-3c5c-40b2-b684-ca80ba443ee5"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884109446.pdf?alt=media&token=3107c342-9d77-46df-876a-8c98242a25c3"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884179955.pdf?alt=media&token=750032db-29a9-48af-bc57-36246f9b29a1"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884239009.pdf?alt=media&token=cc7fd23d-2bf0-4faf-a72c-af38ba4a0482"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884296893.pdf?alt=media&token=30b149ff-6d88-4da3-b57f-a559add28e41"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884359944.pdf?alt=media&token=e3eb027f-7096-454e-b23b-6e4880adddae"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884458469.pdf?alt=media&token=19c0dbba-62b4-4823-9d62-d8a201f39e02"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884536179.pdf?alt=media&token=04aec0cb-c10d-42d0-bbc4-cb841abe27cd"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884604506.pdf?alt=media&token=adddd973-2f28-475d-b055-bf7922576d09"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884664388.pdf?alt=media&token=e01815e9-6ff8-46b9-99ee-9cdc6c2aab52"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884722318.pdf?alt=media&token=4666bbd9-92db-4cad-9685-bd93c102563a"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884791143.pdf?alt=media&token=014e0836-7755-444a-ba43-9d1660bdf7b8"));

        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884877434.pdf?alt=media&token=763319ec-f039-43d9-8d7d-7e00dabc30bd"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657884942674.pdf?alt=media&token=3ccfa93f-cbf7-45ef-b32c-330674cc0e80"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885008886.pdf?alt=media&token=51af93c2-6ec8-4116-a618-82ddd2e14f10"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885080304.pdf?alt=media&token=6b5a7c1c-5ff0-4824-b10f-bfa97e960229"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885141067.pdf?alt=media&token=9bca8a96-5755-410a-9f7b-578b259123b1"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885199844.pdf?alt=media&token=ba958c59-add0-4610-97c5-c145526583b7"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885261030.pdf?alt=media&token=adad7c78-6181-4d6e-a8b8-ee548a86dec6"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885320596.pdf?alt=media&token=93c8981c-07bd-4bc4-b481-00aa9efd1f08"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885382899.pdf?alt=media&token=5ee7bae7-9fab-43d3-848d-061962a52e24"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885465656.pdf?alt=media&token=6d9533aa-0f74-48e4-a676-3d5cc62e53c9"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885521650.pdf?alt=media&token=533bbaee-d46c-48ec-9b76-43bf5b61d905"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885577499.pdf?alt=media&token=0ed3e49d-ca13-4ee3-8fb5-221e005117b8"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885650377.pdf?alt=media&token=96128a47-6ea7-4284-ad22-b4d42d95544c"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885720335.pdf?alt=media&token=854a95c1-c845-47ef-8f18-78ff8d6b923d"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885786270.pdf?alt=media&token=6ea8f939-eb49-41c5-ad05-318d9ef06e95"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885838095.pdf?alt=media&token=6b167f86-18c2-4473-bb78-4ecfd92f8653"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885894267.pdf?alt=media&token=441da15f-d141-4140-9e77-1c93cd0e9852"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657885961809.pdf?alt=media&token=bc32c19b-e200-48c8-9373-4f0be1b91a14"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886090159.pdf?alt=media&token=c29a750e-d314-4dc9-9463-23a8bd6c53f7"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886158810.pdf?alt=media&token=302e9206-2de2-4278-acf0-e399b9085503"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886216534.pdf?alt=media&token=d18dea43-c3c3-4957-b13b-90679bf1153b"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886292593.pdf?alt=media&token=33843335-f0ea-4bfd-b011-e09e50f2905d"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886362957.pdf?alt=media&token=31b24007-6e9a-461f-b862-51adcf4d4141"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886499417.pdf?alt=media&token=45085f36-053a-4077-8ed6-bc75dab9784a"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886564303.pdf?alt=media&token=7f722e54-f4e0-46a6-993f-a7fc8f105ddf"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886726534.pdf?alt=media&token=4a2ab37b-42e4-42cc-b7b3-55ea722f92fd"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657886804594.pdf?alt=media&token=e617136e-e37c-4e5c-b9e0-3c07acd90d26"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896888316.pdf?alt=media&token=2d13f7f2-f126-4252-9ab9-8e18e22215bf"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657896975552.pdf?alt=media&token=29358732-d0c2-4494-84e3-86dffb4c11b5"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897080387.pdf?alt=media&token=82f04b5c-f512-41aa-baf7-224651251a17"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897285191.pdf?alt=media&token=0123ca82-66e6-464f-982c-590f6d101a1a"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897373982.pdf?alt=media&token=775a7be8-9f3e-47ea-a459-8641ec25944c"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897719060.pdf?alt=media&token=7545b2c6-c2cd-4b18-a590-517450d32f33"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897789987.pdf?alt=media&token=3a18b223-8012-4cfd-bc44-54cf3cfaba37"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657897896159.pdf?alt=media&token=b743f321-13f3-4020-aec0-27f7ac7ecdcf"));

        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898785863.pdf?alt=media&token=706116e4-5d97-439a-a0d4-b749be17855e"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898881562.pdf?alt=media&token=d287b0d5-edbc-48b2-92a1-4d260a8136c0"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657898961974.pdf?alt=media&token=98d21294-3be5-4740-90d9-500d1fca6df8"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899021638.pdf?alt=media&token=429eb836-d713-44a5-901c-aee4499df5dc"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899100756.pdf?alt=media&token=009d4826-a6c0-4957-a75f-e6370a33fe2f"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899185245.pdf?alt=media&token=b16eebab-42f1-4180-858c-10a3afb1863d"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899265735.pdf?alt=media&token=7657068e-0633-4463-b0e9-cd80ffa21913"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899348451.pdf?alt=media&token=ce8d0cb9-519a-429d-b532-5fe8af07a088"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899423020.pdf?alt=media&token=75372e2e-d2a4-414f-bf83-4ae086e91655"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899509289.pdf?alt=media&token=736c3b13-9b0f-4da6-9d04-84da62de7afd"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899592718.pdf?alt=media&token=08e69407-1184-4ba1-a461-d93c07508853"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899656999.pdf?alt=media&token=3df3c428-ed95-4be3-9582-b86f60977db4"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899744111.pdf?alt=media&token=d2321124-b207-4daf-ad23-62c415d24cda"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899841241.pdf?alt=media&token=4729378f-5290-4287-9f85-5ce86ef948c2"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657899927457.pdf?alt=media&token=ccbd565f-517d-4ee9-9737-9c10fa2fb9d9"));

        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901108881.pdf?alt=media&token=f33ce941-1cb9-46dd-bf87-e4a00ff64de9"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901209995.pdf?alt=media&token=7e10bc93-104f-46b3-ac3d-1b4939e53046"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901284004.pdf?alt=media&token=f538a6cb-b6a2-411e-90d9-022e23705c5d"));

        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901361204.pdf?alt=media&token=0cde921f-f46f-4a71-b9c1-92c39c9e829c"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901436221.pdf?alt=media&token=ed9b2c74-c320-406e-8bb0-26d40895ebf1"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901515881.pdf?alt=media&token=e1dd3157-25cb-4947-98a4-0555e61b8789"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901726373.pdf?alt=media&token=46bae7b6-3234-4c4c-8403-9fdb8ca0a6f8"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901726373.pdf?alt=media&token=46bae7b6-3234-4c4c-8403-9fdb8ca0a6f8"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901815980.pdf?alt=media&token=bdd6c501-adfa-4b7b-9cf9-a618c5658eff"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657901927655.pdf?alt=media&token=f8f279b1-6426-4380-b295-7302a42dd802"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657902184793.pdf?alt=media&token=56e67840-5d2b-46bc-ae74-01e5be2d33df"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906810137.pdf?alt=media&token=9bf2ce88-5b31-4f15-a5e1-c5d357a9795d"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906894146.pdf?alt=media&token=4786c069-8b0a-4830-8457-68d0ed914b07"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657906964299.pdf?alt=media&token=dbb3cfff-915b-4a6b-a0ba-96cbf5241fc5"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907029123.pdf?alt=media&token=aba40d27-5a8e-4ea8-b3ba-b33e5a31b15a"));

        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907146841.pdf?alt=media&token=9213c640-ec15-4204-b438-075d595192bb"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907222669.pdf?alt=media&token=69cb7600-93dd-42b0-b913-8603f8ac881e"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907299109.pdf?alt=media&token=5b075fe7-8c46-4bfd-9d4e-be9898f2623b"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907361837.pdf?alt=media&token=50fcc2a5-9ab6-42c2-92fa-64fbb918842c"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907426815.pdf?alt=media&token=7fd38898-cef5-435f-a406-1f1ea3d4ed62"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907484476.pdf?alt=media&token=13930011-779e-49b6-8560-daba24a1fd42"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907551043.pdf?alt=media&token=eabfec9c-2805-4d4e-9d55-9db37de9fda9"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907609904.pdf?alt=media&token=5e29b517-f6e8-4a5c-9f31-515b3705ee45"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907665349.pdf?alt=media&token=1e526151-9e38-40df-8951-3037e5fc27e1"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907731926.pdf?alt=media&token=4753ba22-c398-4812-a1ee-95b1cde329cd"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907820739.pdf?alt=media&token=276b1e8e-789c-4995-a871-327cd6ae0346"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907884612.pdf?alt=media&token=3334f076-c6f0-487c-b2f1-b9f0564bec91"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657907944613.pdf?alt=media&token=127dcdf1-1836-4316-8e9e-108503332498"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908036961.pdf?alt=media&token=97047439-5526-48f0-b85c-898227ffba62"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908097459.pdf?alt=media&token=b8ae346b-e1a0-4234-a1c4-1eb0eaae0e70"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908157581.pdf?alt=media&token=c5986374-2d8c-464f-a7d0-50517233ad6d"));

        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908358087.pdf?alt=media&token=c087fcef-3857-4f21-9e4b-2a4dc1059a09"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908421450.pdf?alt=media&token=1369d10c-0b3f-4197-8dc1-7901d59a5988"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908483019.pdf?alt=media&token=dac501dc-a3eb-4d48-a8d9-9e2f0c7d4b2c"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908540997.pdf?alt=media&token=4b59f6fb-69a2-4537-adf1-f6d1e61926d8"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908600007.pdf?alt=media&token=4d47f936-29d9-4532-9af9-78e5342f016c"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908666778.pdf?alt=media&token=67b64f06-18c5-4c92-835f-cf8d32e1cb5c"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908730069.pdf?alt=media&token=807f5885-098d-4aba-bb1d-ed65f44abc24"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908818046.pdf?alt=media&token=30a27d3e-2330-4a72-8cb2-74f0a5deacc4"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908871602.pdf?alt=media&token=b43cd6f8-8096-4118-b6eb-6e4e580fbb6e"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908947842.pdf?alt=media&token=da585e89-1f34-4ee2-a503-0e0d9097bfbd"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6839,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657908993918.pdf?alt=media&token=0fa0bb76-062d-4d92-9c9e-2f598701c65f"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909063529.pdf?alt=media&token=6eacf8a0-eec1-48b4-bd18-e84dc5e187a7"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657909124649.pdf?alt=media&token=1c4bddf9-ec80-456e-84bb-6dcbf291183a"));

        progressDialog.dismiss();
    }



}
