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

import com.example.ign_app.adapter.VideoAdapter;
import com.example.ign_app.networkcalls.VideoIGN;
import com.example.ign_app.R;
import com.example.ign_app.helper.VideoConst;
import com.example.ign_app.videomodel.FeedVideo;
import com.example.ign_app.videomodel.videodata.Assets;
import com.example.ign_app.videomodel.videodata.ThumbnailsVideo;
import com.example.ign_app.videomodel.videodata.VideosData;
import com.example.ign_app.helper.VideoPageScroller;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ign_app.helper.VideoPageScroller.PAGE_START;


public class VideoTab extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private static final String BASE_URL = "https://ign-apis.herokuapp.com/";
    private static final String TAG = "VideoTab";
    RecyclerView recyclerView;
    View view;
    String contentId, title, description, imgUrl, videoUrl;
    LinearLayoutManager layoutManager;
    VideoAdapter videoAdapter;
    int currentStart = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;



    public VideoTab() {
        // Required empty public constructor
    }

    public static VideoTab newInstance(String param1, String param2) {
        VideoTab fragment = new VideoTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingVideoApi(currentStart);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        videoAdapter = new VideoAdapter(getContext(), new ArrayList<VideoConst>());
        recyclerView.setAdapter(videoAdapter);
        recyclerView.addOnScrollListener(new VideoPageScroller(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentStart += 10;
                callingVideoApi(currentStart);
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


    private void callingVideoApi(int index) {

        final int currentPage = index;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                VideoIGN videoIGN = retrofit.create(VideoIGN.class);
                Call<FeedVideo> call = videoIGN.getVideoData(currentPage,10);
                call.enqueue(new Callback<FeedVideo>() {
                    @Override
                    public void onResponse(Call<FeedVideo> call, Response<FeedVideo> response) {
                        final ArrayList<VideoConst> videoConstArrayList  = new ArrayList<>();
                        isLoading = false;
                        Log.d(TAG, "onResponse Server Response: " + response.toString());
                        Log.d(TAG, "onResponse received Information: " + response.body().toString());
                        ArrayList<VideosData> videosDataArrayList = response.body().getData();
                        for(int i=0; i<videosDataArrayList.size() ; i++) {
                            Log.d(TAG, "onResposnse: \n" +
                                    "contentId:" + videosDataArrayList.get(i).getContentId() + "\n" +
                                    "Title:" + videosDataArrayList.get(i).getMetadata().getTitle() + "\n" +
                                    "publishdate:" + videosDataArrayList.get(i).getMetadata().getPublishDate() + "\n" +
                                    "Description:" + videosDataArrayList.get(i).getMetadata().getDescription() + "\n" + "\n");

                            ArrayList<ThumbnailsVideo> thumbnailsVideoArrayList = videosDataArrayList.get(i).getThumbnails();
                            Log.d(TAG, "onResponse:  \n" +
                                    "url:" + thumbnailsVideoArrayList.get(2).getUrl() + "\n");

                            ArrayList<Assets> assetsArrayList = videosDataArrayList.get(i).getAssets();

                            Log.d(TAG, "onResponse:  \n" +
                                    "urlVideo:" + assetsArrayList.get(3).getUrl() + "\n");


                            contentId = videosDataArrayList.get(i).getContentId();
                            title = videosDataArrayList.get(i).getMetadata().getTitle();
                            description = videosDataArrayList.get(i).getMetadata().getDescription();
                            imgUrl = thumbnailsVideoArrayList.get(2).getUrl();
                            videoUrl = assetsArrayList.get(3).getUrl();


                            VideoConst videoConst = new VideoConst(contentId, title, description, imgUrl, videoUrl);
                            videoConstArrayList.add(videoConst);

                        }
                        videoAdapter.addItems(videoConstArrayList);
                        if (currentPage ==300){
                            isLastPage = true;
                        }
                        isLoading=false;

                    }

                    @Override
                    public void onFailure(Call<FeedVideo> call, Throwable t) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Not connected to Internet!");
                        alertDialog.setCancelable(false);

                        alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                callingVideoApi(currentPage);
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
