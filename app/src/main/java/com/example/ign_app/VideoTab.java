package com.example.ign_app;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ign_app.VideoPackage.FeedVideo;
import com.example.ign_app.VideoPackage.VideoData.Assets;
import com.example.ign_app.VideoPackage.VideoData.ThumbnailsVideo;
import com.example.ign_app.VideoPackage.VideoData.VideosData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private static final String BASE_URL = "https://ign-apis.herokuapp.com/";
    private static final String VIDEO_URL = "https://ign-apis.herokuapp.com/videos/";
    private static final String TAG = "VideoTab";
    RecyclerView recyclerView;
    View view;
    ArrayList<VideoConst> videoConstArrayList  = new ArrayList<>();
    String contentId, title, pubhlishdate, description, imgUrl, videoUrl;


    public VideoTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoTab.
     */
    // TODO: Rename and change types and number of parameters
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoIGN videoIGN = retrofit.create(VideoIGN.class);
        Call<FeedVideo> call = videoIGN.getStuff();
        call.enqueue(new Callback<FeedVideo>() {
            @Override
            public void onResponse(Call<FeedVideo> call, Response<FeedVideo> response) {

                Log.d(TAG, "onResponse Server Response: " + response.toString());
                Log.d(TAG, "onResponse received Information: " + response.body().toString());
                ArrayList<VideosData> videosDataArrayList = response.body().getData();
               // Log.d("BCSIZE", String.valueOf(dataArrayList.size()));
                for(int i=0; i<videosDataArrayList.size() ; i++){
                    Log.d(TAG,"onResposnse: \n" +
                            "contentId:" + videosDataArrayList.get(i).getContentId() + "\n" +
                            "Title:" + videosDataArrayList.get(i).getMetadata().getTitle() +"\n" +
                            "publishdate:" + videosDataArrayList.get(i).getMetadata().getPublishDate() + "\n" +
                            "Description:" + videosDataArrayList.get(i).getMetadata().getDescription() + "\n" + "\n" );

                    ArrayList<ThumbnailsVideo> thumbnailsVideoArrayList = videosDataArrayList.get(i).getThumbnails();
                    Log.d(TAG, "onResponse:  \n" +
                            "url:" +  thumbnailsVideoArrayList.get(2).getUrl() + "\n");

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

                VideoAdapter adapter = new VideoAdapter(getContext(), videoConstArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<FeedVideo> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}
