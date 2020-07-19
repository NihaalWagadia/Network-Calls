package com.example.ign_app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ign_app.adapter.ArticleAdapter;
import com.example.ign_app.helper.ArticleData;
import com.example.ign_app.networkcalls.IgnApi;
import com.example.ign_app.helper.PaginationScrollListener;
import com.example.ign_app.R;
import com.example.ign_app.articlemodel.ArticleApiResponse;
import com.example.ign_app.articlemodel.data.Authors;
import com.example.ign_app.articlemodel.data.Data;
import com.example.ign_app.articlemodel.data.Thumbnails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ign_app.helper.PaginationScrollListener.PAGE_START;



public class ArticleTab extends Fragment {

    private static final String BASE_URL = "https://ign-apis.herokuapp.com/";
    private static final String TAG = "ArticleTab";
    RecyclerView recyclerView;
    View view;

    String contentId, headline, urlImage, description, authorName, authorImage, slug, oyo;
    int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    ArticleAdapter adapter;
    LinearLayoutManager layoutManager;

    public ArticleTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingApi(currentPage);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter(getContext(), new ArrayList<ArticleData>());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage = currentPage+10;
                callingApi(currentPage);

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        return view;
    }

    private void callingApi( int index) {

        final int finalCurrentPage = index;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final IgnApi ignApi = retrofit.create(IgnApi.class);
                Log.d("currentPage", String.valueOf(ArticleTab.this.currentPage +1));
                Call<ArticleApiResponse> call = ignApi.getArticleJson(finalCurrentPage,10);
                call.enqueue(new Callback<ArticleApiResponse>() {
                    @Override
                    public void onResponse(Call<ArticleApiResponse> call, Response<ArticleApiResponse> response) {
                        final ArrayList<ArticleData> articleDataArrayList = new ArrayList<>();

                        isLoading=false;
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

                            ArrayList<Thumbnails> thumbnailsArrayList = dataArrayList.get(i).getThumbnails();
                            Log.d(TAG, "onResponse:  \n" +
                                    "url:" + thumbnailsArrayList.get(2).getUrl() + "\n" +
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
                        adapter.addItems(articleDataArrayList);

                        if (finalCurrentPage ==300){
                            isLastPage = true;
                        }
                        isLoading=false;
                    }


                    @Override
                    public void onFailure(Call<ArticleApiResponse> call, Throwable t) {
                        isLoading=false;
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Not connected to Internet!");
                        alertDialog.setCancelable(false);

                        alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callingApi(currentPage);
                            }
                        });
                        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().finish();
                                System.exit(0);

                            }
                        });
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();

                    }
                });
            }
        },1500);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}