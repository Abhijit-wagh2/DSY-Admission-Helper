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

public class Cap3_2018 extends Fragment {
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

    private void initdata() {
        userlist=new ArrayList<model>();
        userlist.add(new model("Government College of Engineering, Amravati",1002,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280111732.pdf?alt=media&token=e464bd3e-8d9a-4b32-b3d7-1b53a69b7c03"));


        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280154049.pdf?alt=media&token=0cf83694-93d7-449f-abb6-6cfccbc7d928"));


        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280202971.pdf?alt=media&token=b37aded9-faef-4767-9c19-1ec9a3b95b32"));


        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280267083.pdf?alt=media&token=752801b9-7c96-4930-ae97-5412e35a646c"));



        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280313441.pdf?alt=media&token=c05793ce-5508-4add-b9b1-3cfd0ce73df5"));






        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280357682.pdf?alt=media&token=b22db5ff-2961-4210-8a44-7e322aebf7bd"));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280396662.pdf?alt=media&token=dfba6942-b6c5-4857-995d-aad0bdb6d3cd"));



        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280429563.pdf?alt=media&token=63f52985-90c8-434b-924c-db1543a8ce5d"));
        userlist.add(new model("Paramhansa Ramkrishna Maunibaba Shikshan Santha's , Anuradha Engineering College, Chikhali",1119,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280486821.pdf?alt=media&token=d486a549-d332-4634-b0ba-2ffdd5d52fcb"));







        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280520998.pdf?alt=media&token=faab8e89-1c72-457a-886b-dc3c725c5cd5"));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280693660.pdf?alt=media&token=637a5be1-e18e-4a7c-a10b-a75005bb0f14"));




        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280730490.pdf?alt=media&token=99c63c4a-4970-4dfe-8c84-620fa07cfe90"));
        userlist.add(new model("G.H. Raisoni college of Engineering & Management, Amravati",1124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280775295.pdf?alt=media&token=14f5c68f-5b69-43a0-9b14-cbd6e06c4a42"));


        userlist.add(new model("Dwarka Bahu Uddeshiya Gramin Vikas Foundation, Rajarshri Shahu College of Engineering, Buldhana",1125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280810456.pdf?alt=media&token=68899979-1415-460e-aeb7-2723b5a64fda"));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280846340.pdf?alt=media&token=2f39a7e1-5b3a-4a54-b9ff-d70af86bd5b6"));



        userlist.add(new model("Jagadambha Bahuuddeshiya Gramin Vikas Sanstha's Jagdambha College of Engineering and Technology, Yavatmal",1127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280884686.pdf?alt=media&token=d4c9dd98-bdfc-442a-becc-880919fd56ac"));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280928516.pdf?alt=media&token=e9c37a26-e574-4bf5-af1e-160662f180b9"));




        userlist.add(new model("Dhamangaon Education Society's College of Engineering and Technology, Dhamangaon",1129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657280963509.pdf?alt=media&token=86689716-590a-4792-830a-ee82ceac12b1"));

        userlist.add(new model("Vision Buldhana Educational & Welfare Society's Pankaj Laddhad Institute of Technology & Management Studies, Yelgaon",1130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281002054.pdf?alt=media&token=5d8991d9-f033-4a4d-ba01-f330eda9dffe"));



        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281160647.pdf?alt=media&token=e6697695-34fc-4753-8305-38ad0091d7f7"));
        userlist.add(new model("Padmashri Dr. V.B. Kolte College of Engineering, Malkapur, Buldhana",1182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281193537.pdf?alt=media&token=51e9ec09-4c39-415f-b311-1448435dee9b"));


        userlist.add(new model("Mauli Group of Institutions, College of Engineering and Technology, Shegaon",1265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281247832.pdf?alt=media&token=58a69e17-1abc-4fc1-8aca-6d27e45bdfd7"));
        userlist.add(new model("Siddhivinayak Technical Campus, School of Engineering & Research Technology, Shirasgon, Nile",1268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281289713.pdf?alt=media&token=6b81c321-d912-4b23-bc19-8e6b9cd3de13"));




        userlist.add(new model("Manav School of Engineering & Technology, Gut No. 1035 Nagpur Surat Highway, NH No. 6 Tal.Vyala, Balapur, Akola",1276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281343899.pdf?alt=media&token=5dc27bf2-b865-4561-a46f-8b105bf82d6c"));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281402439.pdf?alt=media&token=c0fb0781-2f43-431f-b8ce-627044603f58"));





        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281440315.pdf?alt=media&token=0b46faba-77af-432e-ac22-b6462ec58713"));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281475008.pdf?alt=media&token=a7c546e6-cac2-450b-aba2-4189e05392af"));




        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281526546.pdf?alt=media&token=f6989e04-5f9a-4356-a7bc-caf917d83bb4"));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281558102.pdf?alt=media&token=1dbe5b45-980e-4b79-b474-48a3b97be1c8"));




        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281672146.pdf?alt=media&token=e3defb69-8d67-4698-9cd5-c5ea2f3ca70c"));
        userlist.add(new model("Matoshri Pratishan's Group of Institutions (Integrated Campus), Kupsarwadi , Nanded",2116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281717641.pdf?alt=media&token=574cf7a5-7de6-4932-942a-333a26dcdde1"));




        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281804112.pdf?alt=media&token=f15afbc1-e8b6-4b74-b48d-7a9a1c1e1054"));
        userlist.add(new model("Maharashtra College of Engineering, Nilanga",2128,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281853605.pdf?alt=media&token=d66d834e-298f-44cf-a016-458b3a0e5a49"));




        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281889134.pdf?alt=media&token=cafcc498-50b2-4323-b66d-5e55219e9285"));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281920985.pdf?alt=media&token=19d42ff4-97af-47d1-85f7-a08b0a593d41"));




        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657281954957.pdf?alt=media&token=ef0e9033-130b-4633-bc58-62d1948f6918"));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282001356.pdf?alt=media&token=b8b9b0d1-1bdc-480f-ad57-87fd721872f9"));




        userlist.add(new model("Mahatma Basaweshwar Education Society's College of Engineering, Ambejogai",2133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282042873.pdf?alt=media&token=b2e6f11c-918e-4649-b34c-b8468b41cace"));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282080009.pdf?alt=media&token=656b6230-f826-4eb6-a8cf-29f8597e7cf6"));

        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282122900.pdf?alt=media&token=27465d16-33e8-4002-a114-7996e635fa6a"));
        userlist.add(new model("Aditya Engineering College , Beed",2136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282154375.pdf?alt=media&token=6345f582-8575-4fda-81b2-b354f1a65d2b"));




        userlist.add(new model("Matsyodari Shikshan Sansatha's College of Engineering and Technology, Jalna",2138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282242624.pdf?alt=media&token=d6d7ae99-fb34-49ce-beeb-309e8385038e"));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Aurangabad ",2141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282320193.pdf?alt=media&token=81837078-ede1-4397-892c-8d0f0c176989"));




        userlist.add(new model("Maharashtra Education Society's Maharashtra Udayagiri Institute of Management & Technology, Somnathpur",2144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282363253.pdf?alt=media&token=63f4f70a-3d34-463d-ae0f-0772c2d7f531"));
        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282445501.pdf?alt=media&token=1c8e39a4-88ae-440d-984f-6194d9ed79cc"));


        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering, Parbhani",2252,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282506621.pdf?alt=media&token=71d496e8-ad96-40fb-8497-9e7cd8b328da"));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282541931.pdf?alt=media&token=5ee330f0-18d6-412c-b19a-ff37f30193e1"));




        userlist.add(new model("Balaghat Engineering College,Ruddha, Ahmedpur",2255,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282578302.pdf?alt=media&token=2e594101-085b-4a5b-a6c8-870110c77e93"));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282620864.pdf?alt=media&token=ae3b99d7-b33c-428e-b7f1-7a494078fc55"));



        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282668329.pdf?alt=media&token=4fd3e8f7-a79d-41f6-b246-74e981364d30"));
        userlist.add(new model("V.J. Shinde College of Engineering, Osmanabad.",2529,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282706723.pdf?alt=media&token=14244dbc-9b41-43e8-9e93-540e4d9ae53f"));




        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282739650.pdf?alt=media&token=9cd32a65-8fe8-4a8e-8b86-74f8df6d66f6"));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded Vishnupuri, Nanded, Nanded waghala, Nanded",2573,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282792668.pdf?alt=media&token=a5564ccb-f810-42ea-92b4-ebd94cae57af"));



        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282893018.pdf?alt=media&token=2904b7a9-84e5-484c-b2b1-2a1ff567c515"));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657282964139.pdf?alt=media&token=330f870c-2937-4844-9c97-a0df59a7af18"));




        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283008092.pdf?alt=media&token=ad9336c6-ef1c-431e-966d-18541114a21c"));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283049190.pdf?alt=media&token=7f650593-2743-485c-98c1-3b485af5f623"));

        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283135266.pdf?alt=media&token=fa0bfa36-2b66-4f2e-95c5-5e79ea043448"));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283183227.pdf?alt=media&token=27a44657-9461-42bd-81fa-469e50e077c9"));




        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar",3146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283220265.pdf?alt=media&token=6efbf79f-ff2d-4641-8188-793552fca8da"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar Institute of Engineering, Karjat",3147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283263274.pdf?alt=media&token=1124c425-806b-4f03-9f90-2819134ed684"));


        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283303976.pdf?alt=media&token=15c2665e-2c2e-461f-ae5b-7ed6af93b54e"));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283348246.pdf?alt=media&token=40992ec7-9c99-460e-b5d9-e55c3924041d"));




        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283448051.pdf?alt=media&token=f1366c62-910b-40b6-bbf8-98abca3737a8"));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283507603.pdf?alt=media&token=66eb1d66-ca07-4a68-ad62-1023a8cf0aa3"));

        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283548847.pdf?alt=media&token=ab5e9921-1274-458f-9fe3-dd9a8fafe8bb"));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283589902.pdf?alt=media&token=ba72653e-d510-4d39-bbc1-79eb3efb3274"));




        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283721091.pdf?alt=media&token=b85bdfa1-8f64-4b6e-be53-131ffda848a2"));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283782927.pdf?alt=media&token=51b692c9-e239-4f51-a9bf-cbfad35e7574"));

        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283818667.pdf?alt=media&token=98a4432e-87e3-4fdf-963a-8602ad878836"));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283850836.pdf?alt=media&token=4e547795-7803-46e0-a725-3ed654d7987f"));



        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283885485.pdf?alt=media&token=7f2e67e0-ddbf-4bc1-ae8f-186488eaa8ab"));
        userlist.add(new model("Padmabhushan Vasantdada Patil Pratishthans College of Engineering, Sion,Mumbai",3188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657283921023.pdf?alt=media&token=abca111a-c356-44b4-b2f9-d1eff9391ac5"));

        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Belpada, Kharghar, Navi Mumbai",3189,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657284001026.pdf?alt=media&token=ad49354e-5f9a-4ece-a5dc-98c2688e4c17"));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657284042624.pdf?alt=media&token=c31af829-8a6f-4b32-9330-4d06d789f37b"));



        userlist.add(new model("Mandar Education Society's Rajaram Shinde College of Engineering, Pedhambe",3191,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657284093643.pdf?alt=media&token=292e25c2-1e24-482d-a320-ba642c8b06e8"));
        userlist.add(new model("Smt. Indira Gandhi College Of Engineering Ghansoli Navi Mumbai",3192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350072570.pdf?alt=media&token=258715bf-5f96-4424-89d1-27509d291ad1"));

        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai, Thane",3194,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350115961.pdf?alt=media&token=3423716f-4491-4679-8544-3d54e45565c8"));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350157383.pdf?alt=media&token=382b474c-8aae-4e96-a113-1cc802e90e34"));



        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350211137.pdf?alt=media&token=76b4a085-82fe-48df-beef-281bff7dd563"));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350260121.pdf?alt=media&token=07dd1c12-92b6-40b3-b939-903077584829"));

        userlist.add(new model("Hope Foundation and research center's Finolex Academy of Management and Technology, Ratnagiri",3200,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350318899.pdf?alt=media&token=84c4e3b5-f173-45b1-b1a3-711287738356"));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350373617.pdf?alt=media&token=1fd3e1f3-0fc9-41df-8829-5e1eaa32e85b"));



        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350445180.pdf?alt=media&token=85b7040c-b614-48cd-95b3-c22015f6d570"));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350484483.pdf?alt=media&token=0ca541ec-dd2d-4550-9382-d7a6d2a21312"));

        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350532955.pdf?alt=media&token=46a0e029-9b7a-4a2c-9414-9ba9842c9bb5"));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350566886.pdf?alt=media&token=6e894252-8dd6-4755-a170-c581565afb52"));



        userlist.add(new model("M.E.S Pillais College of Engineering ,New Panvel",3207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350599248.pdf?alt=media&token=9150c35c-ad83-4951-b571-546909aa484a"));
        userlist.add(new model("Don Bosco Institute of Technology, Mumbai",3208,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350627393.pdf?alt=media&token=306b1704-c241-47c5-9f6f-efd001ff5f98"));

        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350662203.pdf?alt=media&token=2aa4f7ff-7a18-41f6-b154-061abdbe41ed"));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350864966.pdf?alt=media&token=fba5471b-4969-4254-b798-89f53d4080c3"));

        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657350970715.pdf?alt=media&token=6c5fcd01-0873-4616-adcc-4af4bc62702e"));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351070653.pdf?alt=media&token=f10241a0-d701-47ab-81a8-952360282c03"));

        userlist.add(new model("Bhartiya Vidya Bhavans Sardar Patel Institute of Technology , Andheri, Mumbai",3215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351107159.pdf?alt=media&token=2862aacb-8254-47f8-9314-4a122098d784"));
        userlist.add(new model("Gharda Foundation's Gharda Institute of Technology,Khed, Ratnagiri",3216,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351144072.pdf?alt=media&token=b3817570-8c3b-4c78-9f58-4540f680f836"));

        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College , Shahapur, Asangaon, Thane",3217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351203668.pdf?alt=media&token=d2338014-ab16-4cdd-9904-545de31d6736"));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Technology, Vevoor, Palghar",3218,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351245350.pdf?alt=media&token=2666fa53-346b-468e-8764-17b201e707c1"));


        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute , Sapgaon, Tal. Shahapur",3219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351322105.pdf?alt=media&token=2fcf80e2-e27e-4a90-9513-3285ffdf75ef"));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering , Nasarapur, Chandai, Karjat",3220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351362895.pdf?alt=media&token=79a1cc08-191c-4ad4-a03c-497d2944ec88"));

        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351401426.pdf?alt=media&token=c4406932-7743-4126-ab3f-3d36547fc691"));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351440894.pdf?alt=media&token=86c0540c-7deb-4448-8328-1345bf5a2423"));

        userlist.add(new model("Mahatma Education Society's Pillai's College of HOCL College of Engineering,Panvel",3223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351538497.pdf?alt=media&token=4792478e-713d-4335-9e9f-a921e506a88d"));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351572965.pdf?alt=media&token=f63a44cb-522b-4d96-9475-cf8301e495b7"));

        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657351625571.pdf?alt=media&token=7b0d5b65-0821-407f-bc32-a7a21b0ca096"));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366515203.pdf?alt=media&token=9657428b-98ac-42e0-a7c2-59bc224b5205"));

        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366574956.pdf?alt=media&token=e3096783-66d0-457f-8ffc-1eb4b8ae39aa"));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366640003.pdf?alt=media&token=4298e96a-54c2-42b8-8fca-db3d821e4c4a"));

        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366687852.pdf?alt=media&token=5e4263fc-d0d1-47e5-ae5c-59d681fb0f85"));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366736940.pdf?alt=media&token=df4ce7a6-5d49-40c2-aa18-2ba8a299b1de"));

        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366772773.pdf?alt=media&token=994fba5c-f4c8-4414-aca7-c55d09bba7d1"));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366815113.pdf?alt=media&token=4186428b-3a3e-4365-8d19-6231333a46d5"));

        userlist.add(new model("Universal College of Engineering,Kaman Dist. Thane",3460,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366859167.pdf?alt=media&token=ef7e5788-b8e5-4fa0-8d67-6bd648ce4fcc"));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657366915302.pdf?alt=media&token=aa714d23-4e42-467e-afd8-ffb2c25c7350"));

        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3465,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367073063.pdf?alt=media&token=732ede57-12c1-4e0e-9496-546def9841b3"));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367109059.pdf?alt=media&token=8fc7dc63-2442-453b-baa6-d3ee910fd555"));


        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367157118.pdf?alt=media&token=5455c973-0e53-4c78-8b1a-0a1349cbe0fd"));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367246450.pdf?alt=media&token=37b090e9-6d73-497f-951f-0f9c5179f321"));

        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367297622.pdf?alt=media&token=62b35141-ded1-432a-b32f-4bd1ca6213a1"));
        userlist.add(new model("Government College of Engineering, Chandrapur",4004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367337599.pdf?alt=media&token=caad2627-7f84-4807-89a5-66f2ae482694"));


        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367385829.pdf?alt=media&token=07e5d8e3-b33c-4ebe-a1b4-28c846244d45"));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367667183.pdf?alt=media&token=c897ffa9-f482-4cac-8163-f90558791c01"));

        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367707411.pdf?alt=media&token=5468f149-8dd2-48b6-b93b-9ae986752eed"));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367730627.pdf?alt=media&token=d3e973f2-6eb3-4797-ac7b-52b85dc5e329"));


        userlist.add(new model("Yashwant Rural Education Society's Bapurao Deshmukh Engineering College,Sevagram",4118,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367757290.pdf?alt=media&token=a6d5db6f-a7da-454e-b606-9b372d536bb6"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367786648.pdf?alt=media&token=239d5e50-9d25-4499-a955-642f6d4ea4e8"));

        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367811805.pdf?alt=media&token=9d241969-80c2-4169-91ae-06f51d81f256"));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367845831.pdf?alt=media&token=42b9159a-ad62-45e2-98d9-531e7f86beb0"));


        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367869667.pdf?alt=media&token=7359783f-19a5-4a47-8cf0-c953a0f11b64"));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367902840.pdf?alt=media&token=5c574dc8-6dcd-4b71-b4ab-5ca1fe3e1b73"));

        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367933004.pdf?alt=media&token=e756a4dd-fb43-4bdb-9e9d-d6ab4700a5f5"));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367964655.pdf?alt=media&token=ae8f4bd8-7a16-41e5-a8e0-a381ffb7017b"));


        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institut of Technology, Nagpur",4139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657367986103.pdf?alt=media&token=a44ec533-683a-4f7c-ad0f-e1d833a1b4a7"));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",4141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368009478.pdf?alt=media&token=9794cf4f-934b-4b23-85dc-d46fd1a24246"));


        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368080892.pdf?alt=media&token=9590d27a-97dc-4b21-8056-fdc0d006dbc2"));


        userlist.add(new model("KDK College of Engineering, Umrer, Nagpur",4146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368112103.pdf?alt=media&token=b97348da-ccf3-4bf9-940d-90d24b5770a9"));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368139945.pdf?alt=media&token=dc6191ea-ef7a-4845-af7c-11993e2d66ea"));

        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368162837.pdf?alt=media&token=3f28cf33-e0de-4ce1-9707-659f6f753489"));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368188319.pdf?alt=media&token=f8e7f954-233e-4607-9abf-2e3e355af6db"));


        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute of Engineering and Technology, Gondia",4165,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368211589.pdf?alt=media&token=f9cb2234-ccf1-4bb4-9e8c-9b83d1a62bb3"));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368235737.pdf?alt=media&token=7447850e-3e35-4c73-80b3-4411b42b9332"));

        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpu",4171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368259377.pdf?alt=media&token=07bfe6a5-a404-4261-82e5-8c3dab05939b"));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368283945.pdf?alt=media&token=f9aac719-b84a-47f7-8b52-61d1922329ad"));


        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368317332.pdf?alt=media&token=883d457b-844e-4fd6-93fe-997c976731f4"));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368462081.pdf?alt=media&token=2903e76a-13bd-42f8-b95e-f648af368a76"));

        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368485994.pdf?alt=media&token=0957e976-9644-4b42-b41c-48a0e25b4a5e"));
        userlist.add(new model("Rajiv Gandhi College of Engineering & Research, Hingna Road, Nagpur",4178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368517372.pdf?alt=media&token=e9403d80-016a-4d74-a167-bbcf87ee99c2"));


        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368578113.pdf?alt=media&token=c612c0e0-be5f-4613-9b67-16f47de1e5f4"));
        userlist.add(new model("Shrimati Rajashri Mulak College of Engineering for Womens, Nagpur",4180,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368601457.pdf?alt=media&token=95243adf-8e10-4d5a-bba2-be0bfc068499"));

        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657368655832.pdf?alt=media&token=8e1633bc-6d6a-403d-95bb-4fbf1ec841b9"));
        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657529889883.pdf?alt=media&token=759380fc-2504-4ab5-9bc3-68c29d728020"));


        userlist.add(new model("Dr. Babasaheb Ambedkar College of Engineering and Research, Wanadongri, Nagpur",4187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657529931558.pdf?alt=media&token=9399799c-49a9-4f00-95a0-2c894c3032c8"));
        userlist.add(new model("Krushi Jivan Vikas Pratishthan, Ballarpur Institute of Technology, Mouza Bamn",4188,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657529957877.pdf?alt=media&token=176428b2-bf2c-4fc1-b736-99467bae81df"));

        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati ",4190,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657529984660.pdf?alt=media&token=2cf73caa-71c4-4606-b45b-e7f8d69bb6a9"));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530018259.pdf?alt=media&token=06c39a2f-0c89-4487-aad7-3a58b95895fb"));




        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad Patil College of Engineering, Nagpur",4195,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530065593.pdf?alt=media&token=9ab70a6f-9637-4fe3-a3ac-bd577bef8bd9"));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530095160.pdf?alt=media&token=187224f0-eb66-4ef4-9e13-a8e7a6cb3a2a"));

        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530120947.pdf?alt=media&token=9362bddf-d46e-44df-b6bc-6c1307acd7ff"));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530148838.pdf?alt=media&token=410b7c39-aa22-4b79-933e-2c28194d51c0"));




        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara",4302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530173997.pdf?alt=media&token=e6fc2ba6-df41-4abc-b190-15328ef8d4b0"));
        userlist.add(new model("Vidya Niketan Institute of Engineering and Technology, Nagpur",4303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530203733.pdf?alt=media&token=4c3833ce-c32b-4bca-8f07-0b9406d51e7c"));

        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530329196.pdf?alt=media&token=87617e8d-edd7-4c0a-a58c-6548f370a950"));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530391140.pdf?alt=media&token=50e9c2c1-24d7-44a9-b08f-80a45065c1d9"));


        userlist.add(new model("R.V. Parankar College of Engineering & Technology, Arvi, Dist Wardha",4648,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530430531.pdf?alt=media&token=3fb0f690-d7cf-435f-b5b7-4b985a49f8c3"));
        userlist.add(new model("Bajaj Institute of Technology, Wardha",4649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530455698.pdf?alt=media&token=de8dbd74-c2d7-4d1e-bcea-37e8311b5e00"));

        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530479975.pdf?alt=media&token=0247ef21-5ee3-4e2e-a07d-a7f2eaa6814a"));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530502127.pdf?alt=media&token=b9a816c1-f575-45f9-9fcb-f76356c8fd9b"));


        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530535165.pdf?alt=media&token=8afb199d-1d4d-4117-b168-fbdbe275ecb8"));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530590478.pdf?alt=media&token=9df52b5b-cdb1-49d9-86fb-98b7bf3b8189"));

        userlist.add(new model("G.H. Raisoni Institute of Engineering and Management, Jalgaon",5105,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530617829.pdf?alt=media&token=c8831cb3-f464-487e-9b3c-aebd6f5ed415"));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530662328.pdf?alt=media&token=e6b261dd-4659-43ac-9035-4cf310ab6fa2"));


        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530701964.pdf?alt=media&token=dd30f572-4a0e-407c-b79b-c64b941b563c"));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530789589.pdf?alt=media&token=6eec235e-4dfc-44b6-8b83-a4168ee3cab6"));

        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology and Research Centre, Mahiravani, Nashik",5109,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530815405.pdf?alt=media&token=daac6d68-3e6a-4e5c-b5f1-47ab671d3b0e"));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530841836.pdf?alt=media&token=84deb0ce-066f-4a28-9722-e32ae771cdff"));


        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530867705.pdf?alt=media&token=d7c332a1-9053-428c-8cb5-08d6faca3946"));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530894559.pdf?alt=media&token=5d7483df-46f8-45e9-9859-8c8f67a93dc6"));

        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530918389.pdf?alt=media&token=ad5ea220-aa09-4d66-bc4b-a6d31c842964"));
        userlist.add(new model("Pravara Rural Col of Engg. loni, Pravaranagar, Ahmednagar",5139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530946243.pdf?alt=media&token=6941c132-b81f-4b7f-8889-38fdcd8bc50f"));


        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530970962.pdf?alt=media&token=a61cdd2d-9dc6-4489-b2f1-dc9e9a280732"));
        userlist.add(new model("Sanjivani Rural Education Society's College of Engineering, Kopargaon",5160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657530993294.pdf?alt=media&token=d30cdf3c-7e5c-41ae-8320-6946fe3dd283"));

        userlist.add(new model("Padmashri Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531019804.pdf?alt=media&token=231de889-2b2b-450b-8213-18367f7fec5a"));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531041571.pdf?alt=media&token=ebb0a085-4a86-4f9c-a8de-0f57035f2e17"));


        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531073684.pdf?alt=media&token=1b31023e-c66d-4799-99c8-51f4af28da92"));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531096907.pdf?alt=media&token=73ff5e89-97ce-4405-9953-c27ab5e12abb"));

        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon,Dhule",5169,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531137002.pdf?alt=media&token=c19110fb-4bdc-4827-a319-db925d066004"));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531174021.pdf?alt=media&token=e03bb47c-b5b3-49e1-b2d0-3996f3f1ae0a"));


        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531199682.pdf?alt=media&token=f0df6f6a-8643-4f49-8bdb-7853b63bf7bb"));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531238979.pdf?alt=media&token=e9f61514-8321-4481-8a65-e81f9962b93f"));

        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531273684.pdf?alt=media&token=76fdf476-774c-4f7c-8689-275b3d89bddc"));
        userlist.add(new model("G.H. Raisoni College of Engineering and Management, Ahmednagar",5176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531321961.pdf?alt=media&token=dc26ad89-6155-4988-a334-9d3621de3f91"));


        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531342966.pdf?alt=media&token=8ca70729-d62a-4da7-8dad-aef64f7588ac"));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531371470.pdf?alt=media&token=2602a464-ccae-4bc3-bd1e-2ea5c14a2fcc"));


        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531398405.pdf?alt=media&token=4ce4fae4-01d9-4d9a-9dce-a8b32d7ac109"));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531451857.pdf?alt=media&token=663baed7-9295-486a-9b85-5a2971b0a030"));

        userlist.add(new model("Shri Chhatrapati Shivaji College of Engineering, (Rahuri Factory), Tal.Rahuri, Dist.Ahmednagar",5183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531479425.pdf?alt=media&token=63f7c942-d886-4326-8b06-af150601f4db"));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531539871.pdf?alt=media&token=cdf7a616-ac15-4c98-b79b-21c433085218"));


        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657531579048.pdf?alt=media&token=52ae42a4-77a8-466c-9ff2-9b06bbe290b7"));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657554973499.pdf?alt=media&token=df39ea0c-e0e2-4a9e-a81f-7123c116bb5f"));

        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555022781.pdf?alt=media&token=bbed7d07-70aa-447d-95e7-4577eba6dfa0"));
        userlist.add(new model("Sandip Institute of Engineering & Management, Nashik",5331,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555056811.pdf?alt=media&token=97f6e4c7-507c-48fc-b209-ae8f2b68e039"));


        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555151062.pdf?alt=media&token=f59778f4-c344-4948-b6c4-4b6a731e2d5b"));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555180446.pdf?alt=media&token=2242f041-61f9-4934-9bf4-d3608778ec0a"));

        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555215294.pdf?alt=media&token=40d4af1b-5c90-4089-8b2f-360121b00af4"));
        userlist.add(new model("Krantiveer Vasantrao Narayan Naik Institute of Engineering Education & Research, Nashik.",5390,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555246032.pdf?alt=media&token=ac0f13ae-adbd-4581-8bb0-1bbce583928a"));


        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555276507.pdf?alt=media&token=0c96b010-5782-465e-a093-69f9e38ec979"));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik.",5399,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555310262.pdf?alt=media&token=d53ef77a-f75f-4dc0-997f-bf235cd0cb36"));


        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555338612.pdf?alt=media&token=75556e38-bba2-4f92-876c-207a51a3451e"));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555364383.pdf?alt=media&token=dd63b5db-7823-482c-896c-c3860b33311a"));

        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555364383.pdf?alt=media&token=dd63b5db-7823-482c-896c-c3860b33311a"));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555390192.pdf?alt=media&token=0f91ebc8-7144-457a-9fff-8f8ac8ebaa18"));

        userlist.add(new model("Maulana Mukhtar Ahmad Nadvi Technical Campus, Malegaon.",5411,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555416272.pdf?alt=media&token=99484711-df63-4010-999d-7e725c1b24a6"));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule Survey No. 499/1, Plot No.2, Behind Gurudwara, Mumbai Agra Highway, Dhule",5449,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555546468.pdf?alt=media&token=22c9be8d-7e9b-4052-bda9-73164216c287"));

        userlist.add(new model("Government College of Engineering, Avasari",6004,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555574746.pdf?alt=media&token=83e83235-d162-4135-befb-40bd3cc2564c"));
        userlist.add(new model("Government College of Engineering, Karad",6005,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555595276.pdf?alt=media&token=4d3ae7d7-b1b3-4b2d-8e3f-09a108c65813"));




        userlist.add(new model("College of Engineering, Pune",6006,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555618886.pdf?alt=media&token=720d7e64-8497-4c67-89e4-a502946db803"));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,"6007"));

        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555670670.pdf?alt=media&token=61a9cd58-2e81-4382-9178-84bdf768fe2f"));
        userlist.add(new model("The Shetkari Shikshan Mandal Sangli's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555693422.pdf?alt=media&token=7ec059cb-8630-4b9e-812d-d1d67eea6cd6"));

        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555735319.pdf?alt=media&token=1cf784f2-83ef-494d-ba76-1b9f18785c57"));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555766927.pdf?alt=media&token=bb3cc585-c5d8-416c-b49b-aacdab84f2c0"));




        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555794119.pdf?alt=media&token=2296ce77-c499-46e9-80a7-25a154848045"));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555819646.pdf?alt=media&token=3a17a606-69bf-462e-a3a1-6bf9b1630178"));

        userlist.add(new model("Jaywantrao Sawant College of Engineering,Pune",6145,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555890902.pdf?alt=media&token=6626c623-196f-45e0-83d5-b43a9846584d"));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555928049.pdf?alt=media&token=70712965-8314-48af-b10c-2d9ebd15541c"));

        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555950280.pdf?alt=media&token=431f9681-988e-4b84-8768-ea5018c9abb3"));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657555987412.pdf?alt=media&token=b70877fc-1a7d-4491-be56-bea8d252ff39"));







        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556697837.pdf?alt=media&token=ed1e7df9-adba-4c7a-bc3a-806797ca685e"));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556729563.pdf?alt=media&token=3d5a7c33-7178-4475-8e00-5a6112ab8ed8"));

        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556757530.pdf?alt=media&token=72b53582-2314-42b4-815c-bedb8f853fe0"));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556784976.pdf?alt=media&token=2ff38604-f358-4b95-8643-8539641aa8cf"));

        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556825029.pdf?alt=media&token=582304fb-e16d-4342-bd6a-0acdd6cf09a4"));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556846660.pdf?alt=media&token=2cf4a876-df35-4d3b-b0ac-e9f7fe5eaa80"));





        userlist.add(new model("Chanakya Education Society's Indira College of Engineering & Management, Pune",6179,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556867450.pdf?alt=media&token=1bb9e2ae-fe76-4bf1-b552-3516fcb4d1dd"));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556904390.pdf?alt=media&token=c2b10863-6ab9-4cc0-9c88-ba827a4bfcd5"));

        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556933841.pdf?alt=media&token=eac49448-212f-4a05-96b1-f7182b926f4a"));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657556960919.pdf?alt=media&token=4024b912-e467-43d9-bd87-8b0d9b5c1f4f"));

        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557093534.pdf?alt=media&token=edbafbad-ff45-4eb4-94d9-6bece546478b"));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557118881.pdf?alt=media&token=43f67c44-b96b-4a79-9469-f9e9ee62c2e2"));




        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557146245.pdf?alt=media&token=98c726fe-4954-404d-8128-6de27638c37b"));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557172963.pdf?alt=media&token=0caac676-a9c7-470d-850f-123a4e334398"));

        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Engineering & Technology, Pimpri,Pune",6207,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557203527.pdf?alt=media&token=64c008a9-8e11-4c37-9e56-94867d09314f"));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557231355.pdf?alt=media&token=48168db5-d836-440b-b4cc-948e5811f5de"));

        userlist.add(new model("Sou. Sushila Danchand Ghodawat Cha.Trusts Sanjay Ghodavat Group of Institutions (Integrated Campus), Atigre, Kolhapur",6215,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557259451.pdf?alt=media&token=297b5ffb-59dd-4d7e-b1d7-061f29be19de"));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557289865.pdf?alt=media&token=6381bde8-b43c-49c8-aafe-aad32845ce97"));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602642488.pdf?alt=media&token=406a770c-4925-4766-a5c0-44b112b56ce9"));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602673758.pdf?alt=media&token=5fcda660-77af-4b01-9f2e-f7f7480245f1"));




        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557312150.pdf?alt=media&token=77123d2a-e08a-4597-8ce3-8ba43bdc35d7"));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557337885.pdf?alt=media&token=411e0c47-9fa6-4bb2-8ee5-a201b5234f43"));

        userlist.add(new model("Dattajirao Kadam Technical Education Societys Textile & Engineering Institute, Ichalkaranji",6222,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557397934.pdf?alt=media&token=2189dadd-b758-40bb-8e37-82eae8cf5fe7"));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557433727.pdf?alt=media&token=ba6ac046-4605-4a07-a9fa-3f0d4f18ec31"));

        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557462738.pdf?alt=media&token=b545839c-50c9-447b-8417-ae463f480a84"));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557490448.pdf?alt=media&token=52b7d4b5-1ab3-4509-a073-2d6bdd21c66b"));







        userlist.add(new model("Kolhapur Institute of Technology College of Engineering, Kolhapur",6267,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557513579.pdf?alt=media&token=12970987-003a-4d7c-9d79-e4145293064a"));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557536862.pdf?alt=media&token=9dcd6df0-bcba-4c23-9231-866377987117"));

        userlist.add(new model("Shetkari Shikshan Mandals Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557639559.pdf?alt=media&token=4b929e20-9d54-43e7-89e0-1309de711e3e"));
        userlist.add(new model("Karmaveer Bhaurao Patil College of Engineering and Polytechnic, Satara",6270,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557719584.pdf?alt=media&token=78b1c0fb-5f88-40bb-a181-5b9b9421e38a"));

        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557778773.pdf?alt=media&token=1393dcb1-448e-4754-9259-b0452ba29cd0"));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557803779.pdf?alt=media&token=01345a07-b03f-4212-905e-e49e6f437cee"));

        userlist.add(new model("Bansilal Ramnath Agarawal Charitable Trust's Vishwakarma Institute of Technology, Bibwewadi, Pune",6273,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557882219.pdf?alt=media&token=23d1f280-fd77-4c7f-9229-43d692cf86f1"));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6274,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557910272.pdf?alt=media&token=bd5c47c5-b82a-4149-93a9-187f632f2b84"));

        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557931385.pdf?alt=media&token=09540296-35ab-4763-b5d6-834c7a977c40"));
        userlist.add(new model("MKSSSs Cummins College of Engineering for Women, Karvenagar,Pune",6276,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557955326.pdf?alt=media&token=7faf4eae-7d81-4d2b-a45d-1dbc97840803"));



        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657557979812.pdf?alt=media&token=c7a499c8-f3d7-488d-8ad0-23ce345c4e8a"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558008736.pdf?alt=media&token=3f3a6fc0-5e47-4168-ab5e-23955696056b"));

        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558033173.pdf?alt=media&token=8a1732f6-bb0a-4489-8072-181982c0b19b"));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558056818.pdf?alt=media&token=2c0309da-23e9-4a6c-aab5-96a2e33f89f9"));




        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558078332.pdf?alt=media&token=21ccda65-fe60-43ea-8b19-bec5814509a3"));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558134588.pdf?alt=media&token=80723b6f-a9fb-4e8f-b47b-d9f7eba3ec42"));

        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558160371.pdf?alt=media&token=92c33fd8-7a9b-4fae-a2dd-51ac6a1c4423"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558184992.pdf?alt=media&token=0885b623-f70c-4165-a193-2d46b3ca4c6a"));




        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558209230.pdf?alt=media&token=82018770-b31b-4de8-be31-57f30a4c0417"));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657558252356.pdf?alt=media&token=b9bc9bc0-1a28-41a5-be6c-a58e3be3e977"));

        userlist.add(new model("Zeal Education Society's Dnyanganga College of Engineering & Reserch, Narhe, Pune",6298,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602252215.pdf?alt=media&token=2dfe7f2e-66f0-4fcd-af16-a3a3be8cd6e5"));
        userlist.add(new model("Gourishankar Educational & Charitable Trust's Satara College of Engineering & Management, Limb, Satara",6302,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602282633.pdf?alt=media&token=8c3fca3a-dceb-4a78-916e-364d13d38452"));




        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602304838.pdf?alt=media&token=7690175a-f095-4fd5-87fd-2b7dc4b28c23"));
        userlist.add(new model("Adarsh Institute of Technology & Research Center Vita, Sangli",6304,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602333800.pdf?alt=media&token=7a5c35f9-0f42-4c4e-bf8d-27b02b8e24c1"));

        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara Survey No. 259 At Post. Raigoan Taluka. Javali, District : Satara",6305,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602356186.pdf?alt=media&token=06a900ad-80ac-4b3d-9066-e27ffed8e8e5"));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602382212.pdf?alt=media&token=9a705dda-8d7b-4a85-adfe-993aa2ac5743"));




        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602404586.pdf?alt=media&token=79a63e47-c38c-49e9-bb9e-59a886fcd082"));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602459067.pdf?alt=media&token=816c28a5-f90d-4c06-bc9f-3aa5cf4ad96d"));

        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602486640.pdf?alt=media&token=60c460e9-e50f-4d74-8f64-9b863e2ae18e"));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602512440.pdf?alt=media&token=78ba6f1a-c45d-4602-a9a1-dbb033890884"));







        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602535281.pdf?alt=media&token=1cff2be7-1da6-4948-b183-f15d66b95aa9"));
        userlist.add(new model("Shri Shamrao Patil (Yadravkar)Educational & Charitable Trusts, Sharad Institute of Technology College of Engineering, Yadrav",6317,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602563641.pdf?alt=media&token=1c0d6d1e-a18b-4fce-aaf8-299536d1f0ab"));

        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602587980.pdf?alt=media&token=175b00da-9741-4256-8743-eaf7eabe7fde"));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602612904.pdf?alt=media&token=47f46202-f0de-4d40-b15b-de2ec8a51464"));



        userlist.add(new model("Dr. D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602726562.pdf?alt=media&token=0a0860c4-b931-4fdd-b945-a716299c2188"));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602759713.pdf?alt=media&token=4a320a02-d03d-4280-bbf7-eeda210b1991"));


        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602879740.pdf?alt=media&token=166fa47c-b8be-44bc-8871-4745292c36eb"));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602907763.pdf?alt=media&token=5bffa983-3da3-41bf-b41d-f80ba41b95f2"));

        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602932091.pdf?alt=media&token=00ab4ba1-ec6b-4d68-b141-dad5da303e60"));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602956631.pdf?alt=media&token=06897f00-ef2f-4f86-b4a7-0fd69e2bec14"));


        userlist.add(new model("Jaihind College Of Engineering,Kuran.",6609,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657602982353.pdf?alt=media&token=a6e33815-50a9-4055-866f-513c5fa93b42"));
        userlist.add(new model("Shriram Institute Of Engineering And Technology Centre, Paniv",6618,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603016805.pdf?alt=media&token=7824ee5f-9475-4b91-a272-6e2d6db6739b"));


        userlist.add(new model("DR. D. Y. Patil Institute of Engineering and Technology, Ambi",6620,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603043117.pdf?alt=media&token=16e41cf8-f775-4858-83c1-3e3d36bfe0c1"));
        userlist.add(new model("Institute Of Knowledge-College Of Engineering, Pimpale - Jagtap",6621,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603065777.pdf?alt=media&token=0455c3e7-10d9-421d-add2-4c8dd532f048"));

        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603086862.pdf?alt=media&token=22df4c7f-043d-4812-b405-32a869321716"));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603131052.pdf?alt=media&token=c18be545-1253-4502-9ab5-70cb2653b675"));




        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603150848.pdf?alt=media&token=72b4d922-28b6-43d8-9c74-8e072a2be992"));
        userlist.add(new model("Dhananjay Mahadik Group of Institutions, Kagal, Kolhapur",6629,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603173979.pdf?alt=media&token=336f0771-9ed9-4ce6-a01d-cecf93cde37d"));


        userlist.add(new model("Navsahyadri Education Society's Group of Institutions, Naigaon, Tal. Bhor, Dist. Pune",6632,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603196811.pdf?alt=media&token=466ebf70-6478-4cae-9ff4-22b1264527b6"));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603222681.pdf?alt=media&token=dedabfd0-e184-4ea2-9c5c-713c65ef72c1"));

        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603257757.pdf?alt=media&token=ee6f7669-8366-4953-b5ae-0764fb17d1a9"));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603287368.pdf?alt=media&token=3685389c-a26c-4777-80bb-4f4a07d00ee4"));



        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603313278.pdf?alt=media&token=95f1da88-4cc1-4962-9d12-82903aa1d5cf"));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603343954.pdf?alt=media&token=7578811f-41f6-4280-b075-4ddfc1a61e1b"));


        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe",6649,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603373895.pdf?alt=media&token=2eb70db3-8f03-4cca-a465-b54de17d79e8"));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603399596.pdf?alt=media&token=b829952f-2e07-4f3c-84b3-ba28ba5e8c13"));

        userlist.add(new model("International Institute of Information Technology (I2IT), Pune.",6754,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603427357.pdf?alt=media&token=cd528c1b-2955-40c4-92a3-84c3eebcf678"));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune.",6755,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603450805.pdf?alt=media&token=55eab562-aed6-42ed-8740-0aa42b07d7a2"));

        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603477876.pdf?alt=media&token=38d10743-fdaf-4439-91aa-d9674cad8825"));


        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603499537.pdf?alt=media&token=574ae398-446c-4b94-8a24-144e590307b3"));


        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603536326.pdf?alt=media&token=ad1f3495-b61a-4103-81d9-51d010469b83"));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603568974.pdf?alt=media&token=7ddc5a49-98b6-4744-961a-6eabbd0cb7b4"));

        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli.",6762,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603590678.pdf?alt=media&token=26dccd2f-e27d-45bd-8ae8-84efd2024e37"));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603613211.pdf?alt=media&token=ab99cb2b-cf3c-4fb6-a4c8-e033d6b1746d"));




        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603636910.pdf?alt=media&token=067601e0-ab97-462a-b33a-86830c355645"));


        userlist.add(new model("P.K. Technical Campus, Pune.",6768,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603692756.pdf?alt=media&token=f1b3a17c-66ae-4bbc-859e-6c9099bbb20f"));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune.",6769,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603662174.pdf?alt=media&token=e8948963-d962-44f9-95b4-51e7249820f4"));

        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603779730.pdf?alt=media&token=f8859921-cc85-4ce0-acd6-1c58e4feb0c4"));
        userlist.add(new model("Flora Institute of Technology, Khopi, Near Khed Shivapur Toll Plaza, Pune",6771,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603814178.pdf?alt=media&token=45075261-579a-401c-9064-cfe5946d6fb5"));




        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603837467.pdf?alt=media&token=7caa8f37-5716-4b97-a573-1dcd06dcf7a1"));


        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603867253.pdf?alt=media&token=64171fb6-2447-43df-9b8a-6b076a344fc8"));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603890872.pdf?alt=media&token=79c9be20-ce1a-458c-b4c2-9b1faa00c9ff"));

        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603912900.pdf?alt=media&token=4164f23e-6cf3-4cc1-8c4d-b47e03d771f9"));
        userlist.add(new model("Dr. D. Y. Patil School of Engineering & Technology, Charholi(Bk), Via Lohgaon, Pune",6786,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603937081.pdf?alt=media&token=5d5028e6-01c3-4460-931b-b60d1566702d"));




        userlist.add(new model("D. Y. Patil School of Engineering Academy, Ambi",6787,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657603997793.pdf?alt=media&token=78c3e656-b568-450c-9b9c-e87270ed22e6"));


        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604027664.pdf?alt=media&token=0b51ca9a-94fc-493c-89c6-64628d5c7ba9"));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604075014.pdf?alt=media&token=c5f22897-bc1a-4dc7-be22-393b722f684e"));

        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604104126.pdf?alt=media&token=fc3cf249-dc6b-46a4-827d-ee7212ef47ca"));
        userlist.add(new model("Pad. Dr.D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604131982.pdf?alt=media&token=e412018d-de31-42d4-8ea6-b3922018363a"));





        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604170637.pdf?alt=media&token=42af8c4b-2fa0-4cfd-9c48-d2cd89654520"));


        userlist.add(new model("Keystone School of Engineering, Pune",6808,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604194585.pdf?alt=media&token=f8b5c01d-654f-4cf6-a5b9-733e54cce217"));
        userlist.add(new model("Shivganga Charitable Trust, Sangli Vishveshwarya Technical Campus, Faculty of Degree Engineering, Patgaon, Miraj",6809,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604216408.pdf?alt=media&token=1e3b2c39-7a86-45cb-832a-e40958f5803c"));

        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604297781.pdf?alt=media&token=fe2de119-140c-42c2-a7fe-3df9d508f48c"));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604325152.pdf?alt=media&token=aaac2b0b-7849-4b41-9b70-7d97a21d3333"));





        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604354225.pdf?alt=media&token=06057cce-00b4-4912-a467-80c50c8be5e8"));


        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6839,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604379879.pdf?alt=media&token=044b03d0-2fdf-43e8-a8c0-07cffcf46dfa"));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604402143.pdf?alt=media&token=da4981d1-e5fa-4e57-91f0-d90dd7c40dde"));

        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi Latur Road, MIDC Jamgaon, Tal. Barshi, Dist. Solapur",6901,"https://firebasestorage.googleapis.com/v0/b/dse-application-da44a.appspot.com/o/upload%2F1657604428259.pdf?alt=media&token=395b4da5-5615-4251-879b-daff26372f9a"));


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
        db.collection("Cap32018").orderBy("Institute_Code", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
