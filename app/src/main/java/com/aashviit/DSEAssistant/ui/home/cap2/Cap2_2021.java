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
        userlist.add(new model("Government College of Engineering, Amravati",1002,""));
        userlist.add(new model("Sant Gadge Baba Amravati University,Amravati",1005,""));
        userlist.add(new model("Shri Sant Gajanan Maharaj College of Engineering,Shegaon",1101,""));
        userlist.add(new model("Prof. Ram Meghe Institute of Technology & Research, Amravati",1105,""));
        userlist.add(new model("P. R. Pote (Patil) Education & Welfare Trust's Group of Institution(Integrated Campus),Amravati",1107,""));
        userlist.add(new model("Sipna Shikshan Prasarak Mandal College of Engineering & Technology, Amravati",1114,""));
        userlist.add(new model("Shri Shivaji Education Society's College of Engineering and Technology, Akola",1116,""));
        userlist.add(new model("Janata Shikshan Prasarak Mandal's Babasaheb Naik College Of Engineering, Pusad",1117,""));
        userlist.add(new model("Paramhansa Ramkrishna Maunibaba Shikshan Santha's , Anuradha Engineering College, Chikhali",1119,""));
        userlist.add(new model("Jawaharlal Darda Institute of Engineering and Technology, Yavatmal",1120,""));
        userlist.add(new model("Shri Hanuman Vyayam Prasarak Mandals College of Engineering & Technology, Amravati",1121,""));
        userlist.add(new model("Dr.Rajendra Gode Institute of Technology & Research, Amravati",1123,""));
        userlist.add(new model("Shri. Dadasaheb Gawai Charitable Trust's Dr. Smt. Kamaltai Gawai Institute of Engineering & Technology, Darapur, Amravati",1126,""));
        userlist.add(new model("Prof Ram Meghe College of Engineering and Management, Badnera",1128,""));
        userlist.add(new model("P. R. Pote Patil Institute of Engineering & Research, At Kathora, Amravati",1148,""));
        userlist.add(new model("Sanmati Engineering College, Sawargaon Barde, Washim",1180,""));
        userlist.add(new model("Government College of Engineering, Aurangabad",2008,""));
        userlist.add(new model("Shri Guru Gobind Singhji Institute of Engineering and Technology, Nanded",2020,""));
        userlist.add(new model("University Department of Chemical Technology, Aurangabad",2021,""));
        userlist.add(new model("Everest Education Society, Group of Institutions (Integrated Campus), Ohar",2111,""));
        userlist.add(new model("Shree Yash Pratishthan, Shreeyash College of Engineering and Technology, Aurangabad",2112,""));
        userlist.add(new model("G. S. Mandal's Maharashtra Institute of Technology, Aurangabad",2113,""));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2114,""));
        userlist.add(new model("Deogiri Institute of Engineering and Management Studies, Aurangabad",2126,"h"));
        userlist.add(new model("Mahatma Gandhi Missions College of Engineering, Hingoli Rd, Nanded.",2127,""));
        userlist.add(new model("Maharashtra College of Engineering, Nilanga",2128,""));
        userlist.add(new model("M.S. Bidve Engineering College, Latur",2129,""));
        userlist.add(new model("Terna Public Charitable Trust's College of Engineering, Osmanabad",2130,""));
        userlist.add(new model("Shree Tuljabhavani College of Engineering, Tuljapur",2131,""));
        userlist.add(new model("M.G.M.'s Jawaharlal Nehru Engineering College, Aurangabad",2132,""));
        userlist.add(new model("Peoples Education Society's College of Engineering, Aurangabad",2134,""));
        userlist.add(new model("Hi-Tech Institute of Technology, Aurangabad",2135,""));
        userlist.add(new model("Shri Sai Samajik Vikas Santha's Shri Sai Colllege of Engineering, Paddari Village Tal. & Dist. Aurangabad",2141,""));
        userlist.add(new model("Adarsh Shikshan Prasarak Mandal's K. T. Patil College of Engineering and Technology, Osmanabad",2146,""));
        userlist.add(new model("Aurangabad College of Engineering, Naygaon Savangi, Aurangabad",2250,""));
        userlist.add(new model("Marathwada Shikshan Prasarak Mandal's Shri Shivaji Institute of Engineering and Management Studies, Parbhani",2252,""));
        userlist.add(new model("Vilasrao Deshmukh Foundation Group of Institutions, Latur",2254,""));
        userlist.add(new model("International Centre of Excellence in Engineering & Management, Aurangabad.",2516,""));
        userlist.add(new model("STMEI's Sandipani Technical Campus-Faculty of Engineering, Latur.",2522,""));
        userlist.add(new model("CSMSS Chh. Shahu College of Engineering, Aurangabad",2533,""));
        userlist.add(new model("Gramin College of Engineering, Vishnupuri, Nanded",2573,""));
        userlist.add(new model("Veermata Jijabai Technological Institute(VJTI), Matunga, Mumbai",3012,""));
        userlist.add(new model("Sardar Patel College of Engineering, Andheri",3014,""));
        userlist.add(new model("Dr. Babasaheb Ambedkar Technological University, Lonere",3033,""));
        userlist.add(new model("Usha Mittal Institute of Technology SNDT Women's University, Mumbai",3035,""));
        userlist.add(new model("Manjara Charitable Trust's Rajiv Gandhi Institute of Technology, Mumbai",3135,""));
        userlist.add(new model("Vidyalankar Institute of Technology,Wadala, Mumbai",3139,""));
        userlist.add(new model("Jawahar Education Society's Annasaheb Chudaman Patil College of Engineering,Kharghar, Navi Mumbai",3146,""));
        userlist.add(new model("Mahavir Education Trust's Shah & Anchor Kutchhi Engineering College, Mumbai",3148,""));
        userlist.add(new model("Saraswati Education Society's Saraswati College of Engineering,Kharghar Navi Mumbai",3154,""));
        userlist.add(new model("Ramrao Adik Edu Soc, Ramarao Adik Institute of Tech., Navi Mumbai",3174,""));
        userlist.add(new model("M.G.M.'s College of Engineering and Technology, Kamothe, Navi Mumbai",3175,""));
        userlist.add(new model("Thakur College of Engineering and Technology, Kandivali, Mumbai",3176,""));
        userlist.add(new model("K.J.Somaiya College of Engineering, Vidyavihar, Mumbai",3181,""));
        userlist.add(new model("Thadomal Shahani Engineering College, Bandra, Mumbai",3182,""));
        userlist.add(new model("Anjuman-I-Islam's M.H. Saboo Siddik College of Engineering, Byculla, Mumbai",3183,""));
        userlist.add(new model("Fr. Conceicao Rodrigues College of Engineering, Bandra,Mumbai",3184,""));
        userlist.add(new model("Vivekanand Education Society's Institute of Technology, Chembur, Mumbai",3185,""));
        userlist.add(new model("N.Y.S.S.'s Datta Meghe College of Engineering, Airoli, Navi Mumbai",3187,""));
        userlist.add(new model("Vasantdada Patil Pratishthan's College Of Engineering and Visual Arts, Sion, Mumbai",3188,""));
        userlist.add(new model("Bharati Vidyapeeth College of Engineering, Navi Mumbai",3189,""));
        userlist.add(new model("Terna Engineering College, Nerul, Navi Mumbai",3190,""));
        userlist.add(new model("Smt. Indira Gandhi College of Engineering, Navi Mumbai",3192,""));
        userlist.add(new model("Shivajirao S. Jondhale College of Engineering, Dombivali,Mumbai",3193,""));
        userlist.add(new model("Vidyavardhini's College of Engineering and Technology, Vasai",3194,""));
        userlist.add(new model("Lokmanya Tilak College of Engineering, Kopar Khairane, Navi Mumbai",3196,""));
        userlist.add(new model("Agnel Charities' FR. C. Rodrigues Institute of Technology, Vashi, Navi Mumbai",3197,""));
        userlist.add(new model("Konkan Gyanpeeth College of Engineering, Karjat",3198,""));
        userlist.add(new model("Shri Vile Parle Kelvani Mandal's Dwarkadas J. Sanghvi College of Engineering, Vile Parle,Mumbai",3199,""));
        userlist.add(new model("Rizvi Education Society's Rizvi College of Engineering, Bandra,Mumbai",3201,""));
        userlist.add(new model("Rajendra Mane College of Engineering & Technology Ambav Deorukh",3202,""));
        userlist.add(new model("Atharva College of Engineering,Malad(West),Mumbai",3203,""));
        userlist.add(new model("St. Francis Institute of Technology,Borivali, Mumbai",3204,""));
        userlist.add(new model("S.S.P.M.'s College of Engineering, Kankavli",3206,""));
        userlist.add(new model("Mahatma Education Society's Pillai College of Engineering, New Panvel",3207,""));
        userlist.add(new model("K J Somaiya Institute of Engineering and Information Technology, Sion, Mumbai",3209,""));
        userlist.add(new model("Excelsior Education Society's K.C. College of Engineering and Management Studies and Research, Kopri, Thane (E)",3210,""));
        userlist.add(new model("S.I.E.S. Graduate School of Technology, Nerul, Navi Mumbai",3211,""));
        userlist.add(new model("Xavier Institute Of Engineering C/O Xavier Technical Institute,Mahim,Mumbai",3214,""));
        userlist.add(new model("Bhartiya Vidya Bhavan's Sardar Patel Institute of Technology , Andheri, Mumbai",3215,""));
        userlist.add(new model("Vighnaharata Trust's Shivajirao S. Jondhale College of Engineering & Technology, Shahapur, Asangaon, Dist Thane",3217,""));
        userlist.add(new model("Aldel Education Trust's St. John College of Engineering & Management, Vevoor, Palghar",3218,""));
        userlist.add(new model("Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology, Sapgaon, Tal. Shahapur",3219,""));
        userlist.add(new model("Saraswati Education Society, Yadavrao Tasgaonkar College of Engineering and Management, Nasarapur, Chandai, Karjat",3220,""));
        userlist.add(new model("Late Shri. Vishnu Waman Thakur Charitable Trust, Viva Institute of Technology, Shirgaon",3221,""));
        userlist.add(new model("Haji Jamaluddin Thim Trust's Theem College of Engineering, At. Villege Betegaon, Boisar",3222,"h"));
        userlist.add(new model("Mahatma Education Society's Pillai HOC College of Engineering & Technology, Tal. Khalapur. Dist. Raigad",3223,""));
        userlist.add(new model("Leela Education Society, G.V. Acharya Institute of Engineering and Technology, Shelu, Karjat",3224,""));
        userlist.add(new model("Bharat College of Engineering, Kanhor, Badlapur(W)",3351,""));
        userlist.add(new model("Dilkap Research Institute Of Engineering and Management Studies, At.Mamdapur, Post- Neral, Tal- Karjat, Mumbai",3353,""));
        userlist.add(new model("Shree L.R. Tiwari College of Engineering, Mira Road, Mumbai",3423,""));
        userlist.add(new model("B.R. Harne College of Engineering & Technology, Karav, Tal-Ambernath.",3436,""));
        userlist.add(new model("Anjuman-I-Islam's Kalsekar Technical Campus, Panvel",3439,""));
        userlist.add(new model("Metropolitan Institute of Technology & Management, Sukhalwad, Sindhudurg.",3440,""));
        userlist.add(new model("Vishvatmak Jangli Maharaj Ashram Trust's Vishvatmak Om Gurudev College of Engineering, Mohili-Aghai, Shahpur.",3445,""));
        userlist.add(new model("G.M.Vedak Institute of Technology, Tala, Raigad.",3447,""));
        userlist.add(new model("Universal College of Engineering,Kaman Dist. Palghar",3460,""));
        userlist.add(new model("VPM's Maharshi Parshuram College of Engineering, Velneshwar, Ratnagiri.",3462,""));
        userlist.add(new model("Ideal Institute of Technology, Wada, Dist.Thane",3462,""));
        userlist.add(new model("Vishwaniketan's Institute of Management Entrepreneurship and Engineering Technology(i MEET), Khalapur Dist Raigad",3467,""));
        userlist.add(new model("New Horizon Institute of Technology & Management, Thane",3471,""));
        userlist.add(new model("A. P. Shah Institute of Technology, Thane",3475,""));
        userlist.add(new model("Chhartrapati Shivaji Maharaj Institute of Technology, Shedung, Panvel",3477,""));
        userlist.add(new model("Indala College Of Engineering, Bapsai Tal.Kalyan",3503,""));
        userlist.add(new model("Government College of Engineering, Chandrapur",4004,""));
        userlist.add(new model("Laxminarayan Institute of Technology, Nagpur",4005,""));
        userlist.add(new model("Government College of Engineering, Nagpur",4025,""));
        userlist.add(new model("Kavi Kulguru Institute of Technology & Science, Ramtek",4104,""));
        userlist.add(new model("Shri Ramdeobaba College of Engineering and Management, Nagpur",4115,""));
        userlist.add(new model("Ankush Shikshan Sanstha's G.H.Raisoni College of Engineering, Nagpur",4116,""));
        userlist.add(new model("Bapurao Deshmukh College of Engineering, Sevagram",4118,""));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha, Priyadarshani College of Engineering, Nagpur",4123,""));
        userlist.add(new model("Sanmarg Shikshan Sanstha's Smt. Radhikatai Pandav College of Engineering, Nagpur",4133,""));
        userlist.add(new model("Guru Nanak Institute of Engineering & Technology,Kalmeshwar, Nagpur",4134,""));
        userlist.add(new model("Amar Seva Mandal's Shree Govindrao Vanjari College of Engineering & Technology, Nagpur",4135,""));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sastha, Priyadarshini J. L. College Of Engineering, Nagpur",4136,""));
        userlist.add(new model("Sir Shantilal Badjate Charitable Trust's S. B. Jain Institute of technology, Management & Research, Nagpur",4137,""));
        userlist.add(new model("Jaidev Education Society, J D College of Engineering and Management, Nagpur",4138,""));
        userlist.add(new model("Samridhi Sarwajanik Charitable Trust, Jhulelal Institute of Technology, Nagpur",4139,""));
        userlist.add(new model("Shriram Gram Vikas Shikshan Sanstha, Vilasrao Deshmukh College of Engineering and Technology, Nagpur",441,""));
        userlist.add(new model(" Ankush Shikshan Sanstha's G. H. Raisoni Institute of Engineering & Technology, Nagpur",4142,""));
        userlist.add(new model("Sanmarg Shikshan Sanstha, Mandukarrao Pandav College of Engineering, Bhandara",4143,""));
        userlist.add(new model("Shri. Sai Shikshan Sanstha, Nagpur Institute of Technology, Nagpur",4144,""));
        userlist.add(new model("Wainganga College of Engineering and Management, Dongargaon, Nagpur",4145,""));
        userlist.add(new model("K.D.K. College of Engineering, Nagpur",4147,""));
        userlist.add(new model("Vidarbha Bahu-Uddeshiya Shikshan Sanstha's Tulshiramji Gaikwad Patil College of Engineering & Technology, Nagpur",4151,""));
        userlist.add(new model("Rajiv Gandhi College of Engineering Research & Technology Chandrapur",4163,""));
        userlist.add(new model("Yeshwantrao Chavan College of Engineering,Wanadongri, Nagpur",4167,""));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shikshan Sanstha's , Priyadarshini Institute of Engineering and Technology, Nagpur",4171,""));
        userlist.add(new model("Anjuman College of Engineering & Technology, Nagpur",4172,""));
        userlist.add(new model("ST. Vincent Pallotti College of Engineering & Technology, Nagpur",4174,""));
        userlist.add(new model("JMSS Shri Shankarprasad Agnihotri College of Engineering, Wardha",4175,""));
        userlist.add(new model("Priyadarshini Bhagwati College of Engineering, Harpur Nagar, Umred Road,Nagpur",4177,""));
        userlist.add(new model("Lokmanya Tilak Jankalyan Shiksan Sanstha, Priyadarshini Indira Gandhi College of Engineering, Nagpur",4179,""));
        userlist.add(new model("Sarvasiddhanta Education Soc's Nuva College of Engineering and Technology, Nagpur",4181,""));
        userlist.add(new model("Datta Meghe Institute of Medical Science's Datta Meghe Institute of Engineering & Technology & Research, Savangi (Meghe)",4186,""));
        userlist.add(new model("M.D. Yergude Memorial Shikshan Prasarak Mandal's Shri Sai College of Engineering & Technology, Badravati",4190,""));
        userlist.add(new model("Maitraya Education Society, Nagarjuna Institute of Engineering Technology & Management, Nagpur",4192,""));
        userlist.add(new model("K.D.M. Education Society, Vidharbha Institute of Technology,Umred Road ,Nagpur",4193,""));
        userlist.add(new model("Vidharbha Bahu uddeshiya Shikshan Sanstha's Abha Gaikwad â€“ Patil College of Engineering, Nagpur",4195,""));
        userlist.add(new model("Gurunanak Educational Society's Gurunanak Institute of Technology, Nagpur",4196,""));
        userlist.add(new model("Jai Mahakali Shikshan Sanstha, Agnihotri College of Engineering, Sindhi(Meghe)",4197,""));
        userlist.add(new model("V M Institute of Engineering and Technology, Dongargaon, Nagpur",4285,""));
        userlist.add(new model("Gondia Education Society's Manoharbhai Patel Institute Of Engineering & Technology, Shahapur, Bhandara ",4302,""));
        userlist.add(new model("Cummins College of Engineering For Women, Sukhali (Gupchup), Tal. Hingna Hingna Nagpur",4304,"8f"));
        userlist.add(new model("G.H.Raisoni Academy of Engineering & Technology, Nagpur",4305,""));
        userlist.add(new model("Suryodaya College of Engineering & Technology, Nagpur",4613,"h"));
        userlist.add(new model("University Institute of Chemical Technology, North Maharashtra University, Jalgaon",5003,""));
        userlist.add(new model("Government College of Engineering, Jalgaon",5004,""));
        userlist.add(new model("Shri Shivaji Vidya Prasarak Sanstha's Late Bapusaheb Shivaji Rao Deore College of Engineering,Dhule",5103,""));
        userlist.add(new model("Shramsadhana Bombay Trust, College of Engineering & Technology, Jalgaon",5104,""));
        userlist.add(new model("K. C. E. Societys College of Engineering and Information Technology, Jalgaon",5106,""));
        userlist.add(new model("Shri Gulabrao Deokar College of Engineering, Jalgaon",5107,""));
        userlist.add(new model("Nashik District Maratha Vidya Prasarak Samaj's Karmaveer Adv.Babaurao Ganpatrao Thakare College of Engineering, Nashik",5108,""));
        userlist.add(new model("Sandip Foundation, Sandip Institute of Technology ",5109,""));
        userlist.add(new model("K. K. Wagh Institute of Engineering Education and Research, Nashik",5121,""));
        userlist.add(new model("Jagadamba Education Soc. Nashik's S.N.D. College of Engineering & Reserch, Babulgaon",5124,""));
        userlist.add(new model("Pravara Rural Education Society's Sir Visvesvaraya Institute of Technology, Chincholi Dist. Nashik",5125,""));
        userlist.add(new model("Brahma Valley College of Engineering & Research, Trimbakeshwar, Nashik",5130,""));
        userlist.add(new model("Pravara Rural College of Engineering, Loni, Pravaranagar, Ahmednagar.",5139,""));
        userlist.add(new model("MET Bhujbal Knowledge City MET League's Engineering College, Adgaon, Nashik.",5151,""));
        userlist.add(new model("G. H. Raisoni Institute of Business Management,Jalgaon",5152,""));
        userlist.add(new model("Sanjivani Rural Education Society's Sanjivani College of Engineering, Kopargaon",5160,""));
        userlist.add(new model("Dr. Vithalrao Vikhe Patil College of Engineering, Ahmednagar",5161,""));
        userlist.add(new model("Amrutvahini Sheti & Shikshan Vikas Sanstha's Amrutvahini College of Engineering, Sangamner",5162,""));
        userlist.add(new model("P.S.G.V.P. Mandal's D.N. Patel College of Engineering, Shahada, Dist. Nandurbar",5164,""));
        userlist.add(new model("T.M.E. Society's J.T.Mahajan College of Engineering, Faizpur",5168,"5"));
        userlist.add(new model("Nagaon Education Society's Gangamai College of Engineering, Nagaon, Tal Dist Dhule",5169,""));
        userlist.add(new model("Hindi Seva Mandal's Shri Sant Gadgebaba College of Engineering & Technology, Bhusawal",5170,""));
        userlist.add(new model("Godavari Foundation's Godavari College Of Engineering, Jalgaon",5171,""));
        userlist.add(new model("R. C. Patel Institute of Technology, Shirpur",5172,""));
        userlist.add(new model("SNJB's Late Sau. Kantabai Bhavarlalji Jain College of Engineering, (Jain Gurukul), Neminagar,Chandwad,(Nashik)",5173,""));
        userlist.add(new model("G. H. Raisoni College of Engineering and Management, Ahmednagar",5176,""));
        userlist.add(new model("Matoshri College of Engineering and Research Centre, Eklahare, Nashik",5177,""));
        userlist.add(new model("Vishwabharati Academy's College of Engineering, Ahmednagar",5179,""));
        userlist.add(new model("Gokhale Education Society's, R.H. Sapat College of Engineering, Management Studies and Research, Nashik",5181,""));
        userlist.add(new model("Kalyani Charitable Trust, Late Gambhirrao Natuba Sapkal College of Engineering, Anjaneri, Trimbakeshwar Road, Nashik",5182,""));
        userlist.add(new model("Amruta Vaishnavi Education & Welfare Trust's Shatabdi Institute of Engineering & Research, Agaskhind Tal. Sinnar",5184,""));
        userlist.add(new model("Hon. Shri. Babanrao Pachpute Vichardhara Trust, Group of Institutions (Integrated Campus)-Parikrama, Kashti Shrigondha",5303,""));
        userlist.add(new model("Jamia Institute Of Engineering And Management Studies, Akkalkuwa",5322,""));
        userlist.add(new model("Pune Vidyarthi Griha's College Of Engineering, Nashik",5330,""));
        userlist.add(new model("Adsul's Technical Campus, Chas Dist. Ahmednagar",5380,""));
        userlist.add(new model("Sandip Foundation's, Sandip Institute of Engineering & Management, Nashik",5331,""));
        userlist.add(new model("Shri. Jaykumar Rawal Institute of Technology, Dondaicha.",5381,""));
        userlist.add(new model("Ahmednagar Jilha Maratha Vidya Prasarak Samajache, Shri. Chhatrapati Shivaji Maharaj College of Engineering, Nepti",5382,""));
        userlist.add(new model("K.V.N. Naik S. P. Sansth's Loknete Gopinathji Munde Institute of Engineering Education & Research, Nashik.",5390,""));
        userlist.add(new model("College of Engineering and Technology ,North Maharashtra Knowledge City, Jalgaon",5396,""));
        userlist.add(new model("Sanghavi College of Engineering, Varvandi, Nashik",5399,""));
        userlist.add(new model("Jawahar Education Society's Institute of Technology, Management & Research, Nashik.",5401,""));
        userlist.add(new model("Vidya Niketan College of Engineering, Bota Sangamner",5408,""));
        userlist.add(new model("Rajiv Gandhi College of Engineering, At Post Karjule Hariya Tal.Parner, Dist.Ahmednagar",5409,""));
        userlist.add(new model("Guru Gobind Singh College of Engineering & Research Centre, Nashik.",5418,""));
        userlist.add(new model("Shri. Vile Parle Kelavani Mandal's Institute of Technology, Dhule",5449,""));
        userlist.add(new model("Government College of Engineering & Research, Avasari Khurd",6004,"c"));
        userlist.add(new model("College of Engineering, Pune",6006,""));
        userlist.add(new model("Walchand College of Engineering, Sangli",6007,""));
        userlist.add(new model("Department of Technology, Shivaji University, Kolhapur",6028,""));
        userlist.add(new model("TSSMS's Pd. Vasantdada Patil Institute of Technology, Bavdhan, Pune",6122,""));
        userlist.add(new model("Genba Sopanrao Moze Trust Parvatibai Genba Moze College of Engineering,Wagholi, Pune",6138,""));
        userlist.add(new model("Progressive Education Society's Modern College of Engineering, Pune",6139,""));
        userlist.add(new model("Jaywant Shikshan Prasarak Mandal's,Rajarshi Shahu College of Engineering, Tathawade, Pune",6141,""));
        userlist.add(new model("Genba Sopanrao Moze College of Engineering, Baner-Balewadi, Pune",6144,""));
        userlist.add(new model("JSPM'S Jaywantrao Sawant College of Engineering,Pune",6145,""));
        userlist.add(new model("MIT Academy of Engineering,Alandi, Pune",6146,""));
        userlist.add(new model("Choudhary Attar Singh Yadav Memorial Trust,Siddhant College of Engineering, Maval",6149,""));
        userlist.add(new model("G.H.Raisoni College of Engineering & Management, Wagholi, Pune",6155,""));
        userlist.add(new model("Marathwada Mitra Mandal's College of Engineering, Karvenagar, Pune",6156,""));
        userlist.add(new model("JSPM's Imperial College of Engineering and Research, Wagholi, Pune",6160,""));
        userlist.add(new model("Pimpri Chinchwad Education Trust, Pimpri Chinchwad College of Engineering, Pune",6175,""));
        userlist.add(new model("G. H.Raisoni Institute of Engineering and Technology, Wagholi, Pune",6176,""));
        userlist.add(new model("Sinhgad College of Engineering, Vadgaon (BK), Pune",6177,""));
        userlist.add(new model("Sinhgad Technical Education Society's Smt. Kashibai Navale College of Engineering,Vadgaon,Pune",6178,""));
        userlist.add(new model("Indira College of Engineering & Management, Pune",6179,""));
        userlist.add(new model("Sinhgad Technical Education Society, Sinhgad Institute of Technology and Science, Narhe (Ambegaon)",6182,""));
        userlist.add(new model("Al-Ameen Educational and Medical Foundation, College of Engineering, Koregaon, Bhima",6183,""));
        userlist.add(new model("K. J.'s Educational Institut Trinity College of Engineering and Research, Pisoli, Haveli",6184,""));
        userlist.add(new model("Sinhagad Institute of Technology, Lonavala",6185,""));
        userlist.add(new model("Sinhgad Academy of Engineering, Kondhwa (BK) Kondhwa-Saswad Road, Pune",6187,""));
        userlist.add(new model("Marathwada Mitra Mandal's Institute of Technology, Lohgaon, Pune",6203,""));
        userlist.add(new model("Pune District Education Association's College of Engineering, Pune",6206,""));
        userlist.add(new model("Dr. D. Y. Patil Vidya Pratishthan Society Dr .D. Y. Patil Institute of Technology, Pimpri,Pune",6207,""));
        userlist.add(new model("K. E. Society's Rajarambapu Institute of Technology, Walwa, Sangli",6214,""));
        userlist.add(new model("Shri. Balasaheb Mane Prasarak Mandals, Ashokrao Mane Group of Institutions, Kolhapur",6217,""));
        userlist.add(new model("KSGBS's Bharat- Ratna Indira Gandhi College of Engineering, Kegaon, Solapur",6219,""));
        userlist.add(new model("Shri Vithal Education and Research Institute's College of Engineering, Pandharpur",6220,""));
        userlist.add(new model("Dattajirao Kadam Technical Education Society's Textile & Engineering Institute, Ichalkaranji.",6222,""));
        userlist.add(new model("Pradnya Niketan Education Society's Nagesh Karajagi Orchid College of Engineering & Technology, Solapur",6223,""));
        userlist.add(new model("D.Y. Patil College of Engineering and Technology, Kolhapur",6250,""));
        userlist.add(new model("Walchand Institute of Technology, Solapur",6265,""));
        userlist.add(new model("Kolhapur Institute of Technology's College of Engineering(Autonomous), Kolhapur",6267,""));
        userlist.add(new model("Tatyasaheb Kore Institute of Engineering and Technology, Warananagar",6268,""));
        userlist.add(new model("Shetkari Shikshan Mandal's Pad. Vasantraodada Patil Institute of Technology, Budhgaon, Sangli",6269,""));
        userlist.add(new model("Rayat Shikshan Sanstha's Karmaveer Bhaurao Patil College of Engineering, Satara",6270,"h"));
        userlist.add(new model("Pune Institute of Computer Technology, Dhankavdi, Pune",6271,""));
        userlist.add(new model("Dr. D. Y. Patil Pratishthan's D.Y.Patil College of Engineering Akurdi, Pune",6272,""));
        userlist.add(new model("Pune Vidyarthi Griha's College of Engineering and Technology, Pune",6273,""));
        userlist.add(new model("Shivnagar Vidya Prasarak Mandal's College of Engineering, Malegaon-Baramati",6275,""));
        userlist.add(new model("MKSSS's Cummins College of Engineering for Women, Karvenagar,Pune",6276,""));
        userlist.add(new model("Dr. J. J. Magdum Charitable Trust's Dr. J.J. Magdum College of Engineering, Jaysingpur",6277,""));
        userlist.add(new model("All India Shri Shivaji Memorial Society's College of Engineering, Pune",6278,""));
        userlist.add(new model("Modern Education Society's College of Engineering, Pune",6281,""));
        userlist.add(new model("All India Shri Shivaji Memorial Society's Institute of Information Technology,Pune",6282,""));
        userlist.add(new model("Annasaheb Dange College of Engineering and Technology, Ashta, Sangli",6283,""));
        userlist.add(new model("Vidya Pratishthan's Kamalnayan Bajaj Institute of Engineering & Technology, Baramati Dist.Pune",6284,""));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering for Women, Katraj, Dhankawadi, Pune",6285,""));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering, Kolhapur",6288,""));
        userlist.add(new model("B.R.A.C.T's Vishwakarma Institute of Information Technology, Kondhwa (Bk.), Pune",6289,""));
        userlist.add(new model("Kai Amdar Bramhadevdada Mane Shikshan & Samajik Prathistan's Bramhadevdada Mane Institute of Technology, Solapur",6293,""));
        userlist.add(new model("Zeal Education Society's Zeal College of Engineering & Reserch, Narhe, Pune",6298,""));
        userlist.add(new model("Dr. Ashok Gujar Technical Institute's Dr. Daulatrao Aher College of Engineering, Karad",6303,""));
        userlist.add(new model("Loknete Hanumantrao Charitable Trust's Adarsh Institute of Technology and Research Centre, Vita,Sangli",6304,""));
        userlist.add(new model("S.D.N.C.R.E.S'S.Late Narayandas Bhawandas Chhabada Institute of Engineering & Technology, Satara",6305,""));
        userlist.add(new model("Dhole Patil Education Society, Dhole Patil College of Engineering, Wagholi, Tal. Haveli",6307,""));
        userlist.add(new model("Shanti Education Society, A.G. Patil Institute of Technology, Soregaon, Solapur(North)",6308,""));
        userlist.add(new model("Nutan Maharashtra Vidya Prasarak Mandal, Nutan Maharashtra Institute of Engineering &Technology, Talegaon station, Pune",6310,""));
        userlist.add(new model("Jayawant Shikshan Prasarak Mandal, Bhivarabai Sawant Institute of Technology & Research, Wagholi",6311,""));
        userlist.add(new model("Jaywant College of Engineering & Management, Kille Macchindragad Tal. Walva",6313,""));
        userlist.add(new model("Holy-Wood Academy's Sanjeevan Engineering and Technology Institute, Panhala",6315,""));
        userlist.add(new model("Sharad Institute of Technology College of Engineering, Yadrav(Ichalkaranji)",6317,""));
        userlist.add(new model("Abhinav Education Society's College of Engineering and Technology (Degree), Wadwadi",6318,""));
        userlist.add(new model("Shahajirao Patil Vikas Pratishthan, S.B.Patil College of Engineering, Vangali, Tal. Indapur",6319,""));
        userlist.add(new model("K.J.'s Educational Institute's K.J.College of Engineering & Management Research, Pisoli",6320,""));
        userlist.add(new model("Vidya Vikas Pratishthan Institute of Engineering and Technology, Solapur",6321,""));
        userlist.add(new model("Shree Gajanan Maharaj Shikshan Prasarak Manda'l Sharadchandra Pawar College of Engineering, Dumbarwadi",6322,""));
        userlist.add(new model("D. Y. Patil College of Engineering, Ambi, Talegaon, Maval",6323,""));
        userlist.add(new model("Rajgad Dnyanpeeth's Technical Campus,Dhangwadi, Bhor",6324,""));
        userlist.add(new model("Alard Charitable Trust's Alard College of Engineering and Management, Pune",6325,""));
        userlist.add(new model("Shri Pandurang Pratishtan, Karmayogi Engineering College, Shelve, Pandharpur",6326,""));
        userlist.add(new model("Nutan College of Engineering and Research, Talegaon Dabhade Tal. Maval, Pune",6419,""));
        userlist.add(new model("Shree Santkrupa Shikshan Sanstha, Shree Santkrupa Institute Of Engineering & Technology, Karad",6466,""));
        userlist.add(new model("Samarth Education Trust's Arvind Gavali College Of Engineering Panwalewadi, Varye,Satara.",6545,""));
        userlist.add(new model("Jaihind College Of Engineering,Kuran ",6609,""));
        userlist.add(new model("D. Y. Patil Institute of Engineering and Technology, Ambi",6620,""));
        userlist.add(new model("I.S.B.& M. School of Technology, Nande Village",6622,""));
        userlist.add(new model("Universal College of Engineering & Research, Sasewadi",6625,""));
        userlist.add(new model("Dattakala Group Of Institutions, Swami - Chincholi Tal. Daund Dist. Pune",6628,""));
        userlist.add(new model("KJEI's Trinity Academy of Engineering, Yewalewadi, Pune",6634,""));
        userlist.add(new model("Samarth Group of Institutions, Bangarwadi, Post Belhe Tal. Junnar Dist. Pune",6635,""));
        userlist.add(new model("N. B. Navale Sinhgad College of Engineering, Kegaon, solapur",6640,""));
        userlist.add(new model("S K N Sinhgad College of Engineering, Korti Tal. Pandharpur Dist Solapur",6643,""));
        userlist.add(new model("Shri. Ambabai Talim Sanstha's Sanjay Bhokare Group of Institutes, Miraj",6644,""));
        userlist.add(new model("TSSM's Bhivarabai Sawant College of Engineering and Research, Narhe, Pune",6649,""));
        userlist.add(new model("Dr. D. Y. Patil School OF Engineering, Lohegaon, Pune",6732,""));
        userlist.add(new model("International Institute of Information Technology (IÂ²IT), Pune.",6754,""));
        userlist.add(new model("JSPM Narhe Technical Campus, Pune",6755,""));
        userlist.add(new model("Fabtech Technical Campus College of Engineering and Research, Sangola",6756,""));
        userlist.add(new model("Yashoda Technical Campus, Wadhe, Satara.",6757,""));
        userlist.add(new model("Sahyadri Valley College of Engineering & Technology, Rajuri, Pune.",6758,""));
        userlist.add(new model("Shree Ramchandra College of Engineering, Lonikand,Pune",6759,""));
        userlist.add(new model("Nanasaheb Mahadik College of Engineering,Walwa, Sangli",6762,""));
        userlist.add(new model("Phaltan Education Society's College of Engineering Thakurki Tal- Phaltan Dist-Satara",6766,""));
        userlist.add(new model("Suman Ramesh Tulsiani Technical Campus: Faculty of Engineering, Kamshet,Pune",6767,""));
        userlist.add(new model("P.K. Technical Campus, Pune.",6768,""));
        userlist.add(new model("Rasiklal M. Dhariwal Sinhgad Technical Institutes Campus, Warje, Pune",6769,""));
        userlist.add(new model("SKN Sinhgad Institute of Technology & Science, Kusgaon(BK),Pune.",6770,""));
        userlist.add(new model("NBN Sinhgad Technical Institutes Campus, Pune",6772,""));
        userlist.add(new model("D.Y.Patil Education Society's,D.Y.Patil Technical Campus, Faculty of Engineering & Faculty of Management,Talsande,Kolhapur.",6780,""));
        userlist.add(new model("Bhagwant Institute of Technology, Barshi",6781,""));
        userlist.add(new model("Sahakar Maharshee Shankarrao Mohite Patil Institute of Technology & Research, Akluj",6782,""));
        userlist.add(new model("Dr. D. Y. Patil Educational Academy's, D.Y.Patil School of Engineering Academy, Ambi",6787,""));
        userlist.add(new model("Anantrao Pawar College of Engineering & Research, Pune",6794,""));
        userlist.add(new model("Shri.Someshwar Shikshan Prasarak Mandal, Sharadchandra Pawar College of Engineering & Technology, Someshwar Nagar",6795,""));
        userlist.add(new model("Bharati Vidyapeeth's College of Engineering,Lavale, Pune",6796,""));
        userlist.add(new model("Dnyanshree Institute Engineering and Technology, Satara",6797,""));
        userlist.add(new model("Dr. D.Y.Patil Institute of Engineering, Management & Reseach, Akurdi, Pune",6802,""));
        userlist.add(new model("Sant Gajanan Maharaj College of Engineering, Gadhinglaj",6803,""));
        userlist.add(new model("Keystone School of Engineering, Pune",6808,""));
        userlist.add(new model("Vidya Prasarini Sabha's College of Engineering & Technology, Lonavala",6815,""));
        userlist.add(new model("Pimpri Chinchwad Education Trust's Pimpri Chinchwad College Of Engineering And Research, Ravet",6822,""));
        userlist.add(new model("Dr.D.Y.Patil College Of Engineering & Innovation,Talegaon",6834,""));
        userlist.add(new model("Dr. D Y Patil Pratishthan's College of Engineering, Kolhapur",6869,""));
        userlist.add(new model("Dr. A. D. Shinde College Of Engineering, Tal.Gadhinglaj, Kolhapur",6878,""));
        userlist.add(new model("MAEER's MIT College of Railway Engineering and Research, Jamgaon, Barshi",6901,""));
        userlist.add(new model("Shree Siddheshwar Women's College Of Engineering Solapur.",6938,""));
        progressDialog.dismiss();
    }



}
