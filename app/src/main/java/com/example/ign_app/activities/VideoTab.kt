package com.example.ign_app.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ign_app.R
import com.example.ign_app.adapter.VideoAdapter
import com.example.ign_app.helper.PaginationScrollListener
import com.example.ign_app.helper.VideoData
import com.example.ign_app.helper.VideoPageScroller
import com.example.ign_app.networkcalls.VideoIGN
import com.example.ign_app.videomodel.FeedVideo
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class VideoTab : Fragment() {

    lateinit var recyclerView: RecyclerView
    var contentId: String? = null
    var title: String? = null
    var description: String? = null
    var imgUrl: String? = null
    var videoUrl: String? = null
    var layoutManager: LinearLayoutManager? = null
    lateinit var videoAdapter: VideoAdapter
    var currentStart = VideoPageScroller.PAGE_START
    private var isLastPage = false
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callingVideoApi(currentStart)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_video_tab, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewVid)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        videoAdapter = VideoAdapter(context!!, ArrayList())
        recyclerView.adapter = videoAdapter
        recyclerView.addOnScrollListener(object : VideoPageScroller((recyclerView.layoutManager as LinearLayoutManager)!!) {
            override fun loadMoreItems() {
                this@VideoTab.isLoading = true
                currentStart += 10
                callingVideoApi(currentStart)
                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage("Loading")
                progressDialog.setCancelable(false)
                progressDialog.show()
                Handler().postDelayed({progressDialog.dismiss()},3000)
            }

            override val isLastPage: Boolean = false

            override val isLoading: Boolean = false
        })
    }




    private fun callingVideoApi(index: Int) {
        Handler().postDelayed({
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val videoIGN = retrofit.create(VideoIGN::class.java)
            val call = videoIGN.getVideoData(index, 10)

                call!!.enqueue(object : Callback<FeedVideo> {

                    override fun onResponse(call: Call<FeedVideo>, response: Response<FeedVideo>) {
                        val videoDataArrayList = ArrayList<VideoData>()
                        isLoading = false
                        Log.d(TAG, "onResponse Server Response: $response")
                        Log.d(TAG, "onResponse received Information: " + response.body().toString())
                        val videosDataArrayList = response.body()!!.data
                        for (i in videosDataArrayList.indices) {
                            Log.d(TAG, """
     onResposnse:
     contentId:${videosDataArrayList[i].contentId}
     Title:${videosDataArrayList[i].metadata!!.title}
     publishdate:${videosDataArrayList[i].metadata!!.publishDate}
     Description:${videosDataArrayList[i].metadata!!.description}


     """.trimIndent())
                            val videoThumbnailsArrayList = videosDataArrayList[i].thumbnails
                            Log.d(TAG, """
     onResponse:
     url:${videoThumbnailsArrayList!![2].url}

     """.trimIndent())
                            val videoAssetsArrayList = videosDataArrayList[i].assets
                            Log.d(TAG, """
     onResponse:
     urlVideo:${videoAssetsArrayList!![3].url}

     """.trimIndent())
                            contentId = videosDataArrayList[i].contentId
                            title = videosDataArrayList[i].metadata!!.title
                            description = videosDataArrayList[i].metadata!!.description
                            imgUrl = videoThumbnailsArrayList[2].url
                            videoUrl = videoAssetsArrayList[3].url
                            val videoData = VideoData(contentId!!, title!!, description!!, imgUrl!!, videoUrl!!)
                            videoDataArrayList.add(videoData)
                        }
                        videoAdapter.addItems(videoDataArrayList)
                        if (index == 300) {
                            isLastPage = true
                        }
                        isLoading = false
                    }

                    override fun onFailure(call: Call<FeedVideo>, t: Throwable) {
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.setTitle("Not connected to Internet!")
                        alertDialog.setCancelable(false)
                        alertDialog.setPositiveButton("Retry") { dialogInterface, i -> callingVideoApi(index) }
                        alertDialog.setNegativeButton("Exit") { dialogInterface, i ->
                            activity!!.finish()
                            System.exit(0)
                        }
                        val alertDialog1 = alertDialog.create()
                        alertDialog1.show()
                    }
                })


        }, 1500)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val BASE_URL = "https://ign-apis.herokuapp.com/"
        private const val TAG = "VideoTab"

    }
}

private fun <T> Call<T>.enqueue(callback: Callback<FeedVideo>) {

}
