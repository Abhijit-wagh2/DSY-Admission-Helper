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

public class Cap1_2019 extends Fragment {
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
     // EventChangeListener();

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
        db.collection("Cap12019").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657694471501.pdf?alt=media&token=5c4cfc92-d57b-4f73-b6c5-8f150386e89a"));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657694522202.pdf?alt=media&token=1c966479-c86d-4448-8797-8b82a4e483bc"));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657694606393.pdf?alt=media&token=f2648c95-3662-4c3c-ad9b-b95276a960f8"));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657694657530.pdf?alt=media&token=ce70225d-c550-4fb5-9eea-94bbfc5bf582"));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657714192871.pdf?alt=media&token=ebc7e29e-53f1-4e95-8dda-d80038e62e91"));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657714237464.pdf?alt=media&token=9f9e54ca-403f-48d6-b4bc-04073283440c"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657714281359.pdf?alt=media&token=e0d485ed-94e9-45fc-9385-926a2c7ab5c7"));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657714326972.pdf?alt=media&token=287f5a78-c5e4-449d-b6bc-82dc9095acfa"));

        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657714984672.pdf?alt=media&token=6cb878aa-3d96-44d7-b0fd-8b4f7a111fd5"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715028263.pdf?alt=media&token=713c462b-a04a-425d-ad04-5bfa9112ce8c"));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715071007.pdf?alt=media&token=3a55557d-9fe4-470e-8ec0-6548ad845ce9"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715151631.pdf?alt=media&token=dbc36f13-062a-4822-8cfb-7e554e4fa81c"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715245341.pdf?alt=media&token=d8c42409-60e9-4927-9555-10c37333b72a"));

        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715372820.pdf?alt=media&token=e74ddac9-3ab1-4fee-bc09-17942f3dfb01"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715619026.pdf?alt=media&token=799852ff-bb63-4832-a69a-70e272498d03"));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715675807.pdf?alt=media&token=960f3f08-854c-41f1-b4b5-3b3807c5ffa1"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715723433.pdf?alt=media&token=44cd768f-cb9e-42af-8549-13d8b9cda840"));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715773343.pdf?alt=media&token=73919792-9855-42c1-9070-6530e839ce6c"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715814312.pdf?alt=media&token=81f0fe28-6b2c-45f4-9fed-686aef35446c"));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715869116.pdf?alt=media&token=b87aca2e-f9c2-4af5-9df8-e325b40a0b2a"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657715917880.pdf?alt=media&token=5c1f12dd-51d5-493b-b1e4-4950b3d4d90f"));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716000779.pdf?alt=media&token=8ff61af1-0f7a-4200-b51a-df5dedf0c180"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716040960.pdf?alt=media&token=215bf0f5-9786-4fc7-8db3-c539c2c74f48"));

        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716078925.pdf?alt=media&token=908fe188-3e74-4a07-929b-28e088c2a69d"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716120625.pdf?alt=media&token=46145899-5d32-439a-8278-4b708f3b0aa8"));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716163497.pdf?alt=media&token=d95a4857-9a5c-471e-ac6c-ef693c5f0669"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716201213.pdf?alt=media&token=1c0921fc-59f0-4c1d-8eaf-51cf0df9916b"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716297602.pdf?alt=media&token=ba7a798d-80a9-4bbb-b0f8-51ebd981ff58"));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716343880.pdf?alt=media&token=da90123c-1000-4f25-b92f-02fc00bd4d17"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716517960.pdf?alt=media&token=4987688c-4952-4a8f-9350-a897e36b43fe"));

        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716566032.pdf?alt=media&token=b6659253-e69b-43d7-913b-826f7579fe53"));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716611582.pdf?alt=media&token=3f23301f-7a79-4a78-bf4f-2d13d309ed9c"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716656797.pdf?alt=media&token=f4ce7180-8001-4fda-99c7-e54c00ae3d28"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716756490.pdf?alt=media&token=2add4401-a5e1-4377-8d95-b770c8f92dda"));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716796407.pdf?alt=media&token=9e93fe7d-1945-4bbf-b78d-15ffa3ecdee7"));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716844739.pdf?alt=media&token=c799a1c9-4623-4eb7-a43f-578b5972498b"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716896791.pdf?alt=media&token=30e395c9-4aae-4843-b3b9-0003dda12d59"));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657716943959.pdf?alt=media&token=a90547b6-9358-4005-b317-890553b80cb1"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717015194.pdf?alt=media&token=6817f33f-6556-428f-948f-c64729de0296"));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717069394.pdf?alt=media&token=4515325e-d615-4742-8a88-0f0f843e54be"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717110219.pdf?alt=media&token=97fc3f85-3d63-4361-9aee-2b5517370346"));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717152133.pdf?alt=media&token=f69507ce-3a81-4b0a-8c0f-1fbdb727c1fa"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717195117.pdf?alt=media&token=5111fd0c-f17e-4919-a21c-d1d2a16dc9cf"));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717236248.pdf?alt=media&token=56d4ebe9-9374-4ae2-b312-245f0af4da48"));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717288290.pdf?alt=media&token=4d6129f8-a263-4f30-bf55-46d329dac93d"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717333740.pdf?alt=media&token=7f62f63f-a12f-4870-8728-ba5dfbcebd83"));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717377923.pdf?alt=media&token=12a426d2-63b0-4bf7-b8d1-c0235da12f46"));

        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717525866.pdf?alt=media&token=fbebe88a-ddef-4729-b6ea-bc6d1f2a4fd6"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717576751.pdf?alt=media&token=b5b00da8-c047-44e6-a39e-253977d937a0"));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717620775.pdf?alt=media&token=3c77e902-0857-4b78-b174-3e90a427da82"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717683150.pdf?alt=media&token=5d46068d-90c9-4081-9504-114ae8d59407"));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657717730817.pdf?alt=media&token=70c84f90-d4c0-44d2-8837-af1ee3640181"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657772842453.pdf?alt=media&token=d5b7d12b-a74d-4093-bf5e-c84549a00ddd"));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657772891762.pdf?alt=media&token=f122bbf5-bf1a-48ea-ab4e-f631425698c5"));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657781992803.pdf?alt=media&token=4a8d8ae8-09ea-4852-bb91-2c08c14b8707"));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782039015.pdf?alt=media&token=3e714525-f9a8-4fd3-b076-ee2d72c76918"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782086452.pdf?alt=media&token=1ba803a2-b33a-4bb9-9858-c127a45b61a6"));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782125305.pdf?alt=media&token=2b049ed8-d678-4acc-8c17-5264976aaa66"));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782234522.pdf?alt=media&token=0719cf9e-d215-43d6-b8fe-d8b5523e9721"));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782171144.pdf?alt=media&token=b218e085-cd81-435c-96f7-37def406a75f"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782386108.pdf?alt=media&token=9ed50c13-5926-45e4-b5d1-5c1721366dde"));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782425789.pdf?alt=media&token=547d56db-3b1c-4ee4-bbda-1ab4cc2ab566"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782475227.pdf?alt=media&token=ff891f87-e46a-407e-aced-242adc2b677a"));

        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782643511.pdf?alt=media&token=d6c49ee8-72bd-45da-b90d-967f37e82661"));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657782982729.pdf?alt=media&token=5fba4268-df78-4074-bbb5-81ab561956ac"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783113304.pdf?alt=media&token=985818b0-6e0b-48fe-9f9a-c97a218dc981"));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783161010.pdf?alt=media&token=8e26d834-4cac-4d63-9cb3-90767c8576d0"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783225958.pdf?alt=media&token=ee1491e8-0742-46e4-a2aa-76dd465db8ce"));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783270539.pdf?alt=media&token=80e251be-f4e3-41f6-95a9-2b5fb801cb66"));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783410632.pdf?alt=media&token=8e1e8347-46d1-4c68-bb6c-31a645b63e50"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783473064.pdf?alt=media&token=f495afdc-8597-4ff2-a64d-6585241e4ff8"));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783517401.pdf?alt=media&token=80b6a70a-7e8d-47bf-a840-20f4ac845dbb"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783561595.pdf?alt=media&token=5fd9a9c1-d6a3-4648-87a3-2b751576d515"));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783601757.pdf?alt=media&token=e3a24400-c445-483d-944b-b1f2312f8b04"));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783701956.pdf?alt=media&token=27799c96-d0a6-4fe9-9b20-7a7e804993e4"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783745609.pdf?alt=media&token=e4ddc184-443c-4ce2-87e6-0abe70e367ee"));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783808562.pdf?alt=media&token=b3ba3139-b9fb-4382-b7c8-8038d647bbb9"));

        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783856822.pdf?alt=media&token=ab744b2f-4a05-4b05-8f77-a722fa8ffc8e"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783897069.pdf?alt=media&token=cdc20650-24d5-442f-b0bb-b83908f623d4"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783939584.pdf?alt=media&token=2234e195-4e9a-43cf-abe6-a9f276ca3ba9"));

        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657783992430.pdf?alt=media&token=6b64bad5-4baf-44d2-bd6d-0df04178e298"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784032637.pdf?alt=media&token=7c64cd61-2322-4e5a-b456-6f6de3f29358"));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784079747.pdf?alt=media&token=8ed37b31-4e8e-4774-89d7-849c6fae78eb"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784122504.pdf?alt=media&token=e1969c00-68e4-42de-afa5-fad32537b819"));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784184145.pdf?alt=media&token=1570edac-27cf-45d4-9d46-a3103b7b292d"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784235898.pdf?alt=media&token=c291036c-d2ed-4e83-a51d-ce6355122dab"));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784281272.pdf?alt=media&token=50174dfc-06b8-4b7b-b75c-ea58b33063f7"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784335517.pdf?alt=media&token=6a94c1ec-b24b-4576-a9b3-b002e4752ea0"));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784379487.pdf?alt=media&token=6e5c9030-9218-4fd2-bc69-8b026b44ddf7"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784420187.pdf?alt=media&token=9c1ccfec-bf00-458f-99dd-41a1e9e0e033"));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657784464445.pdf?alt=media&token=2c3ac062-bcd5-4cca-a93f-32f398e3011e"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812408172.pdf?alt=media&token=014d68f8-4d05-4cfb-8369-27af4591fc5e"));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812461480.pdf?alt=media&token=5b3c422c-0076-4dcc-9618-7d6f40a4e4cb"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812508911.pdf?alt=media&token=bbe13049-f807-453f-8e94-0f55755509ba"));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812547952.pdf?alt=media&token=fb6d8ce5-202d-44b1-a6e1-95a522e034dd"));

        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812588610.pdf?alt=media&token=6c8cbee7-5db9-4771-8b70-208fc6ae40ff"));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812629115.pdf?alt=media&token=9cca08af-e7aa-44b5-8d75-5d6edf75f77e"));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812694281.pdf?alt=media&token=41c5cc60-434f-4eea-a0a4-f1ce701e0838"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812733400.pdf?alt=media&token=132fff7d-f9a3-4e63-a163-30e6e25e2390"));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812775874.pdf?alt=media&token=dda2b276-83ac-4049-875e-f456ec478509"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812813111.pdf?alt=media&token=9726293e-3ee1-4dd6-b454-a37c3e8d796e"));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812857664.pdf?alt=media&token=560bf53d-0452-4eec-83c0-5abd9f2bcee3"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812894937.pdf?alt=media&token=965a87bc-8ebb-4d36-8be2-0997fb381a7d"));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657812992687.pdf?alt=media&token=42c751e1-c8ac-4f78-91d3-1b16707e362a"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813059435.pdf?alt=media&token=0372a042-cdf4-42ff-914b-a32008a64efb"));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813103986.pdf?alt=media&token=cc56d1fa-c301-4b27-9e45-5074e9556bed"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813177031.pdf?alt=media&token=22417f39-b176-4e65-8a07-a37834fcd347"));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813229254.pdf?alt=media&token=710d23a2-6150-43e0-8a54-4d4b13fdae5c"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813269463.pdf?alt=media&token=682035e4-097c-440e-ba99-06a8ff8b52f9"));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813311479.pdf?alt=media&token=396a3e4c-f34a-4a36-8dfb-72662b1af094"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813350927.pdf?alt=media&token=b5c4015b-e368-445b-9dc1-6b383cb3236d"));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813390286.pdf?alt=media&token=ef446fb6-fc76-44d0-9537-7eb3eb019e72"));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813431150.pdf?alt=media&token=36f5b9a4-8860-4a1e-8e72-cdeda3559b46"));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813482061.pdf?alt=media&token=26230946-d3e0-4f01-b491-30ca0dd00c47"));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813519143.pdf?alt=media&token=71c688cb-8a44-4ef7-aa5c-8d8f1c104a50"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813568850.pdf?alt=media&token=a610efa2-02aa-4c67-8dc7-24e466e76361"));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813611649.pdf?alt=media&token=483c2bcb-4ccc-4b1d-9f17-5fd490f296a9"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813658217.pdf?alt=media&token=06bec78a-9aa2-406e-82f6-d66b666aaaa6"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813698716.pdf?alt=media&token=71a70118-f689-468b-b23e-ca5c32b3055d"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813766807.pdf?alt=media&token=a92f346e-6410-4211-869c-f72dbb7bfa40"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813849677.pdf?alt=media&token=a850a783-b531-46d1-a41e-050650093763"));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813890336.pdf?alt=media&token=9c586239-7f2a-4ab0-bb79-867ea5aca7f4"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657813985457.pdf?alt=media&token=bdab2bac-9223-4571-9ab6-9fdaf971de16"));
        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814073565.pdf?alt=media&token=2bc9c95c-fe62-4f3d-86a5-969f9234033c"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814359879.pdf?alt=media&token=0734b958-765b-4b1d-bb36-1abfc85a0613"));

        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814526140.pdf?alt=media&token=e2ca4899-5fc2-489d-b65c-6d42b1c1458e"));
        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814749844.pdf?alt=media&token=e3007eb0-60d9-4ef9-b2dc-a08d6f51cea8"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814855892.pdf?alt=media&token=94267667-1c11-4bf7-b6e8-9a83c3c4185d"));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814922198.pdf?alt=media&token=8fc68de1-a86d-4d74-8cfd-d8e49ae8b971"));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657814962533.pdf?alt=media&token=63d21ee4-ae12-40a7-9c4a-0cf7356b5dd2"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815001952.pdf?alt=media&token=2f695f22-698e-4cd1-bb67-930175ea3969"));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815091986.pdf?alt=media&token=13928126-ebfe-44b1-8cc5-13a3c21deffa"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815156552.pdf?alt=media&token=8cfd25f7-5f5e-4b43-ba81-2e4378079793"));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815203592.pdf?alt=media&token=51111024-070c-4c13-ae44-7ee151525737"));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815257842.pdf?alt=media&token=7080b29c-57fc-4162-a12c-c2929e0f9a47"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815299396.pdf?alt=media&token=b109aa90-d04a-4773-a857-31f9ac8a1c69"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815354892.pdf?alt=media&token=eac2b34a-5d4f-4fe0-9d42-b97af4c0c917"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815478887.pdf?alt=media&token=1369e55b-5774-4535-a448-107ef758d2f2"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815519394.pdf?alt=media&token=00f1b0cc-1f8d-4aa7-86b4-c2725dc68b3b"));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815566471.pdf?alt=media&token=16025834-ffda-4790-956a-e4ed7025eb01"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815607208.pdf?alt=media&token=21fb56a7-b001-4ca5-afb3-0c9f6eccea8c"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815694817.pdf?alt=media&token=2a7b6752-02bd-47d1-beac-126014b666f9"));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815732408.pdf?alt=media&token=6f140843-fd53-4fb9-a5a4-f572ae0e5b28"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815771867.pdf?alt=media&token=5bb979ea-d51a-47c2-bf39-b531a8a3c81b"));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815816020.pdf?alt=media&token=31c2a0ec-f323-42ee-af85-ab606c883997"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815861116.pdf?alt=media&token=246c065c-c6c9-4af1-a5be-ed56e59e56f4"));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815921441.pdf?alt=media&token=506771b2-0624-4532-8be7-fbffac9c473c"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815958849.pdf?alt=media&token=ce809d31-4dd1-430f-a394-5d86275a14e3"));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657815998295.pdf?alt=media&token=e0d5e692-7004-4c5f-8d25-be60c60b7e30"));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816046533.pdf?alt=media&token=ded9901b-f784-4cdf-a487-71d03273650f"));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816090917.pdf?alt=media&token=1024391c-b592-4314-aa4a-94d65adaacc8"));

        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816130173.pdf?alt=media&token=b8ee4c54-6f1d-4fe1-98ea-46f081455a2a"));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816183016.pdf?alt=media&token=84c88533-31bc-4c96-82f6-8b03c6a7ecb2"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816223048.pdf?alt=media&token=cf4a61c5-e1fd-4a88-9a3e-70c9056ee92a"));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816267304.pdf?alt=media&token=1a83628c-af78-4c69-a01e-8a96c0f7d606"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816321554.pdf?alt=media&token=6aed68d7-2a27-48d4-8b12-048ec1d64da7"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816365102.pdf?alt=media&token=a3976026-c535-44d1-b698-546b87278c7a"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816404125.pdf?alt=media&token=f98e8777-e136-407a-8c08-a4ded9df96fa"));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816446771.pdf?alt=media&token=bb81a3a4-5d8d-4093-958a-6e2e272ba7bf"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816488540.pdf?alt=media&token=162d8063-2898-4627-baeb-895ebd51a389"));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816535119.pdf?alt=media&token=f8d4cc36-2320-46cd-af1c-29aa4ab50e7f"));
        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816585586.pdf?alt=media&token=cf2c11c9-19c0-4532-a7d6-ec37646070d0"));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816626612.pdf?alt=media&token=b9dfab7d-507a-4ede-8456-ac215809b693"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816664876.pdf?alt=media&token=8d6f7235-4c66-4960-9f75-c29811bf7fb0"));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816702631.pdf?alt=media&token=e1f0e7c9-2f64-4148-8b17-0f032ec03a1d"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816756942.pdf?alt=media&token=e6b8895a-ad12-4916-8e1e-7576e0b4bb24"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816800136.pdf?alt=media&token=b0add622-431e-4ce7-a744-a55f243efa6e"));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816844987.pdf?alt=media&token=90c1b95c-3e61-4992-bdc1-765c5dc33ede"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816895416.pdf?alt=media&token=1ddc9d33-6558-4e43-bf0a-da19e5a4598d"));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816933753.pdf?alt=media&token=43934df1-41ac-40c8-89a0-ecd4d565c8cc"));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817017254.pdf?alt=media&token=836ede11-1e0c-4a8d-bf8b-ebef13f37002"));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657816971083.pdf?alt=media&token=2b245359-1d9d-49bd-92e5-175c6a52a312"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817055133.pdf?alt=media&token=2d452a12-b1ee-4194-928f-3af2166caf1e"));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817095409.pdf?alt=media&token=888ee69a-d0ba-43b7-a776-0fa53500e210"));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817139994.pdf?alt=media&token=b54d4f9c-7140-4f33-9ae3-b6b4209d83db"));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817184077.pdf?alt=media&token=bcc45405-8412-46a7-8ee6-d34429c05238"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817221312.pdf?alt=media&token=1c3bf21a-b42f-4d31-9e48-91f6bf57f65b"));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817277047.pdf?alt=media&token=7020f2d8-258a-4b57-9238-226b262aeaad"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817354772.pdf?alt=media&token=e1158423-86a3-46a4-a4cb-12ae414a20d3"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817392599.pdf?alt=media&token=d8d88b01-ed3c-4559-a3b5-e803a77e46cc"));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817469700.pdf?alt=media&token=d4310665-f9e2-4a4a-afc2-7a396dbc644d"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817504416.pdf?alt=media&token=85a966f8-f5c4-45f2-a2ed-67f5717cf56d"));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657817547870.pdf?alt=media&token=c7032bbf-f303-48d7-9028-23e1bd83d4b2"));
        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657868806044.pdf?alt=media&token=143ecb9a-fe35-4b9d-87a7-0e5d066882b0"));

        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657868806044.pdf?alt=media&token=143ecb9a-fe35-4b9d-87a7-0e5d066882b0"));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657868857329.pdf?alt=media&token=1c656b8a-f8cf-484c-9a6d-c52292b8ef6b"));

        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657868943195.pdf?alt=media&token=35f74ec3-62d2-4b95-8b72-814daa6eeade"));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657868987646.pdf?alt=media&token=bbfdea76-eefe-4617-982d-c224a29991da"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888652219.pdf?alt=media&token=0abe2706-4f53-4346-9a7a-c4846e1b6380"));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888726590.pdf?alt=media&token=2548031e-7e9a-47d6-8f4a-3763264904d5"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888778124.pdf?alt=media&token=fccd0545-fa79-46c7-825b-eba0ecb91d41"));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888815392.pdf?alt=media&token=113a8a80-492c-44d9-b256-46bf4cc2d0ad"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888853348.pdf?alt=media&token=1034e49c-0be7-4d07-8cb7-2e5bad055565"));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888895790.pdf?alt=media&token=d4022093-8a2b-4cd1-a817-4168a59630de"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888934183.pdf?alt=media&token=99d80feb-9b16-4210-817d-40b0fee6c9e5"));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657888972083.pdf?alt=media&token=7d1e9fc1-63f1-4669-84bc-698d7ad855a5"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889011774.pdf?alt=media&token=aaed3f8c-0b2e-4c21-b941-68324b6b79d2"));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889054923.pdf?alt=media&token=df4b60e8-8127-4ac7-8907-241c0028bf41"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889095812.pdf?alt=media&token=f2d8f63c-267f-4244-a0a6-4aa271b1b293"));
        userlist.add(new model("Indira College of Engineering & Management, Pune",6179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889207475.pdf?alt=media&token=85c26a7f-91c2-48e0-bdaa-f48b039dc7d1"));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889248830.pdf?alt=media&token=4a2b8885-0f83-430b-a479-043768d301d2"));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889292860.pdf?alt=media&token=de467fc7-6b68-42c1-be5a-a7a66116c536"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889358153.pdf?alt=media&token=2cddba53-1ba2-4786-a6e2-dbcedfab2889"));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889400367.pdf?alt=media&token=8972db17-a5d8-4bad-a3ed-9e851f892293"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889437783.pdf?alt=media&token=b5c56cdd-2b7c-4a4e-8cad-b74b43995ba2"));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889477630.pdf?alt=media&token=dcdf0fc9-1681-4155-be64-707c4fcb5a1c"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889516031.pdf?alt=media&token=ab0b5883-006f-4e87-a224-fdf8636d452b"));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889597209.pdf?alt=media&token=1110bdb3-bb0e-496d-b2fa-a9b68769c282"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889640322.pdf?alt=media&token=93dac770-e881-4cfc-9f8e-39fb158f8c9b"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889678012.pdf?alt=media&token=876e2025-7c7a-481b-b056-4ec53b8f0e69"));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889715533.pdf?alt=media&token=464c4d7c-27b1-4551-8272-3831f25a87b8"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889753902.pdf?alt=media&token=7927b32a-7afd-4ffd-8dd5-bde8469e0fa4"));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889791397.pdf?alt=media&token=776800be-d89a-44e1-8c57-654b7a3dc437"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889829557.pdf?alt=media&token=52795b23-a9ee-4c3f-861e-0842d444045b"));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889871722.pdf?alt=media&token=d2c3a862-3bd9-48b0-95bf-e106844403f1"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889909167.pdf?alt=media&token=3e264537-ec3e-4745-8ff9-3c6730d6decc"));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889944548.pdf?alt=media&token=e5c0396d-1231-4168-ae2e-479c704123fa"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657889991851.pdf?alt=media&token=92e57622-2aba-4453-9412-443f161d6355"));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890033379.pdf?alt=media&token=29c0cf92-8cfb-4c18-9ef4-b8e951c1ac7d"));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890070614.pdf?alt=media&token=9597a72d-fbc4-4a6e-870d-9b459ce74d72"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890110240.pdf?alt=media&token=cb0fe3a8-c376-4505-b7e7-5f55f30310fe"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890171819.pdf?alt=media&token=59755ba6-703c-4861-960e-fbb3ae984311"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890222671.pdf?alt=media&token=7dcded68-c53d-4088-98d1-1d63e8e5bbec"));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890260565.pdf?alt=media&token=82bf3fa3-abcf-4439-95f4-ea9f514dd9b6"));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890353533.pdf?alt=media&token=835e6f61-022e-4210-abae-f82881abe385"));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890396739.pdf?alt=media&token=f04ddca4-e4dc-4e80-99a1-3200f895cae0"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890437831.pdf?alt=media&token=cd064e55-65d7-4fb3-a4e1-6064063c7413"));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890491095.pdf?alt=media&token=8a7d97c7-407a-43ab-9ba9-ce887d2cfb7b"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890531065.pdf?alt=media&token=95a88674-4534-42a9-8aed-0fdc9c6ba9f7"));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890567969.pdf?alt=media&token=30c792df-edd1-4c36-a96f-a00794a09bb6"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890608141.pdf?alt=media&token=6be9c10b-20a4-458d-989f-a9336be64e88"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890644259.pdf?alt=media&token=450f75d7-d0f7-48e5-bec3-67c19865c7ca"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890686562.pdf?alt=media&token=a207976b-393d-4d28-ac23-8a1f836c1893"));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890726014.pdf?alt=media&token=17b5fc0c-855c-4e34-bb8d-bb920ee3053b"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890767464.pdf?alt=media&token=d0b66287-28a6-41b9-8ee6-3511a3d31ec0"));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890805770.pdf?alt=media&token=55678153-dfb8-45ed-8a57-49d7fcf3bef3"));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890845301.pdf?alt=media&token=ad3bb741-846b-4513-aef6-bd35630e0f8e"));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657890949630.pdf?alt=media&token=4a0d35ea-b53a-48f7-8463-2e3afaaee111"));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891111856.pdf?alt=media&token=6990d1be-72cd-4681-b2c9-ce14a7042584"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891150582.pdf?alt=media&token=4cfbea41-0f0d-415b-a75c-f43511791ccf"));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891241979.pdf?alt=media&token=83bbaad2-c282-41d1-8012-3363bf69989b"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891328618.pdf?alt=media&token=01f551e2-e7d9-4909-8131-c4b9a47985ce"));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891371594.pdf?alt=media&token=30fc65f2-f755-42fa-9f59-845b159b6e98"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891408776.pdf?alt=media&token=80e64941-30e5-4d67-a7d1-ddb282c72396"));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891451276.pdf?alt=media&token=f8b72104-bf8a-4678-89ef-476925efbcd4"));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891588447.pdf?alt=media&token=f1abdafb-2094-49a9-9430-d49436fe1efa"));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891627066.pdf?alt=media&token=08694316-8b23-4fe4-8b54-e5af5506087b"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891661786.pdf?alt=media&token=44417824-81f3-437c-8673-693b8fba1915"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891698620.pdf?alt=media&token=8652114c-7dcb-432e-8878-efd9a5a52799"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891736167.pdf?alt=media&token=cbef048b-e14a-40da-9c7d-87a69721c85a"));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891786153.pdf?alt=media&token=d084623f-d1f7-42d7-b172-478284c40db4"));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891828926.pdf?alt=media&token=68722b7c-3a26-4242-a342-b77eb493e1fc"));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891864552.pdf?alt=media&token=9fb73c94-1e74-48af-8c12-782658e167e3"));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891898129.pdf?alt=media&token=10ce3a39-51ea-4f7e-b1bc-5a88b0150501"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891944538.pdf?alt=media&token=cd3c9e86-bba6-44db-9b72-d67387e6fe4f"));

        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657891983101.pdf?alt=media&token=074862a0-e168-44a3-a217-2d17060e83f0"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892023824.pdf?alt=media&token=91671725-3e4a-4472-a7bf-3b96768f9059"));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892064814.pdf?alt=media&token=5c0f157c-94f9-43ab-8806-50d46bffed55"));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892113206.pdf?alt=media&token=144d8461-20b2-449c-a619-513a4067e70c"));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892161836.pdf?alt=media&token=bce28a07-3196-4d3c-8de4-8bbaf362e883"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892232691.pdf?alt=media&token=1ff508a5-88b9-42d9-87b1-6fc2cd1b2f99"));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892264470.pdf?alt=media&token=6cb64d23-a89b-43ca-9369-71a759ae036d"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892338830.pdf?alt=media&token=5b63a6d6-2741-4580-8d6e-d0df6af555d0"));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892373530.pdf?alt=media&token=85b4db04-66a4-42c5-8c62-3925dbc848ea"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892413346.pdf?alt=media&token=8c50c201-2347-448d-bd4d-d28bdcb117c3"));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892459421.pdf?alt=media&token=e2e1f42a-b6d7-431c-bbb0-fd7a4382bf4c"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892495971.pdf?alt=media&token=22fc6500-a63b-4757-9257-c1f57958033e"));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892534193.pdf?alt=media&token=1db13b22-5508-4c85-a472-439ee00837ec"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892590774.pdf?alt=media&token=264fa428-4700-4eac-9170-7461c0c0efce"));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892633956.pdf?alt=media&token=edc6733a-38c4-4b99-a203-94ef1c010c1e"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892719959.pdf?alt=media&token=1d9d5f0f-1409-4460-95f5-11e8e8d2d7af"));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892766454.pdf?alt=media&token=00f3230f-2f82-4d24-a7b7-08c8d08bc049"));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892806071.pdf?alt=media&token=11096189-7699-4a47-bb77-d7a73d983d9a"));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892844109.pdf?alt=media&token=8b6a081b-1346-42bf-b17e-39f47484d71f"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892915523.pdf?alt=media&token=b486ee3b-bf49-4899-aed3-09b88c405395"));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657892953248.pdf?alt=media&token=a97cab0b-1986-4e43-a564-9d022762fb81"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893235381.pdf?alt=media&token=66718f32-306e-41ae-b942-8d7014f248b9"));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893277523.pdf?alt=media&token=683481b7-7d08-4838-9279-51f493f0b512"));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893344146.pdf?alt=media&token=36f3bb0b-f76e-4548-804b-f91318aa28fb"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893384772.pdf?alt=media&token=6a085af1-4bee-43fd-926e-2b85d98b5cd7"));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893419460.pdf?alt=media&token=670b1641-b4c4-4760-873b-807dd2f648ff"));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893459332.pdf?alt=media&token=0cfc097c-8949-4671-bfd0-18230f4ea3ad"));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893501224.pdf?alt=media&token=d5d2d203-767f-44eb-aa98-9df4f6ac491d"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893549165.pdf?alt=media&token=4b12d565-1465-4d34-b6c8-57977e438908"));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893596012.pdf?alt=media&token=8123bb7d-d919-4ddb-82d1-6edce6add4ea"));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893700978.pdf?alt=media&token=302b3e70-31da-41d0-8e66-7b054a1be0b5"));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893744990.pdf?alt=media&token=59a97007-bdf6-4227-ad29-6042cf55ef60"));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893778166.pdf?alt=media&token=e1164ece-3b3c-4cba-b1a9-da185f693e06"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893817768.pdf?alt=media&token=425d768e-8f62-4b83-84a4-3ebaf666db5f"));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893853564.pdf?alt=media&token=ee76c683-6013-4818-9f5c-bff5113f6580"));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893890056.pdf?alt=media&token=459322dc-99ed-48f3-83b1-a756e72743cf"));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893928167.pdf?alt=media&token=8aad7143-4f2a-4009-905f-7827649d6a91"));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657893993486.pdf?alt=media&token=42721f71-48f2-47d7-9769-bf09f9989e2d"));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894026495.pdf?alt=media&token=36390e51-0d46-4f5c-8aa0-405356381dfa"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894065210.pdf?alt=media&token=98875486-6ec1-477d-8a4d-080265aeb1ef"));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894107018.pdf?alt=media&token=86a28b5b-151d-4857-855e-1b1de7abd1f2"));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6869,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894145270.pdf?alt=media&token=f53aeb0a-c70c-473f-901d-27b55e5ff5ff"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894182899.pdf?alt=media&token=9d2f796c-3391-4474-8c36-85e2c58a59d2"));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657894223814.pdf?alt=media&token=cef3cee0-c657-45ec-bb67-a6e7ab33265f"));

        progressDialog.dismiss();
    }



}
