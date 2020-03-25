package com.example.ign_app;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.ign_app.CommentPackage.content.ContentComment;
import com.example.ign_app.model.Feed;
import com.example.ign_app.model.data.Authors;
import com.example.ign_app.model.data.Data;
import com.example.ign_app.model.data.Thumbnails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private static final String BASE_URL = "https://ign-apis.herokuapp.com/";
    private static final String ARTICLE_URL = "https://ign-apis.herokuapp.com/articles/";
    private static final String BASEURL2 = "https://ign-apis.herokuapp.com/";
    private static final String TAG = "ArticleTab";
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<String> arrayList = new ArrayList<>();
    View view;
    ArrayList<ArticleData> articleDataArrayList = new ArrayList<>();
    ArrayList<CommentData> commentDataArrayList = new ArrayList<>();
    ArrayList<ContentComment> contentCommentArrayList;
    String contentId, headline, urlImage, description, authorName, authorImage, slug;
    String idss = "";
    private RequestQueue mQueue;
    Boolean isScrolling = false;
    int currentItems, totalItems, ScrolloutItems;
    public static int START_INDEX = 1;


    public ArticleTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleTab newInstance(String param1, String param2) {
        ArticleTab fragment = new ArticleTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IgnApi ignApi = retrofit.create(IgnApi.class);
        Call<Feed> call = ignApi.getStuff(START_INDEX,5);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse Server Response: " + response.toString());
                Log.d(TAG, "onResponse received Information: " + response.body().toString());
                ArrayList<Data> dataArrayList = response.body().getData();
                Log.d("BCSIZE", String.valueOf(dataArrayList.size()));
                for(int i=0; i<dataArrayList.size() ; i++){
                    Log.d(TAG,"onResposnse: \n" +
                            "contentId:" + dataArrayList.get(i).getContentId() + "\n" +
                            "Headline:" + dataArrayList.get(i).getMetadata().getHeadline() +"\n" +
                            "publishdate:" + dataArrayList.get(i).getMetadata().getPublishDate() + "\n" +
                            "Description:" + dataArrayList.get(i).getMetadata().getDescription() + "\n" +
                            "Slug:" + "https://ign.com/articles/"+dataArrayList.get(i).getMetadata().getSlug() + "\n" );

//                    idss += dataArrayList.get(i).getContentId()+",";
//                    Log.d("Collectionsof",idss);
                    ArrayList<Thumbnails> thumbnailsArrayList = dataArrayList.get(i).getThumbnails();
                    Log.d(TAG, "onResponse:  \n" +
                            "url:" + thumbnailsArrayList.get(0).getUrl() + "\n" +
                            "size:" +  thumbnailsArrayList.get(0).getSize() + "\n" +
                            "width:" +  thumbnailsArrayList.get(0).getWidth() + "\n" +
                            "height" +  thumbnailsArrayList.get(0).getHeight() + "\n");
                    ArrayList<Authors> authorsArrayList = dataArrayList.get(i).getAuthors();

                    Log.d(TAG, "onResponse:  \n" +
                            "name:" + authorsArrayList.get(0).getName() + "\n" +
                            "thumbnail" + authorsArrayList.get(0).getThumbnail() + "\n");

                    contentId = dataArrayList.get(i).getContentId();
                    headline = dataArrayList.get(i).getMetadata().getHeadline();
                    description = dataArrayList.get(i).getMetadata().getDescription();
                    urlImage = thumbnailsArrayList.get(0).getUrl();
                    authorName = authorsArrayList.get(0).getName();
                    authorImage = authorsArrayList.get(0).getThumbnail();
                    slug = "https://ign.com/articles/" + dataArrayList.get(i).getMetadata().getSlug();
                    ArticleData articleData = new ArticleData(contentId, headline, description, urlImage, authorName, authorImage, slug);
                    articleDataArrayList.add(articleData);

                }
                ArticleAdapter adapter = new ArticleAdapter(getContext(), articleDataArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d(TAG, "onFailure:Something is wrong " + t.getMessage());
                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_article_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}
