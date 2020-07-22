package com.example.ign_app.activities

import android.app.AlertDialog
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
import com.example.ign_app.adapter.ArticleAdapter
import com.example.ign_app.articlemodel.ArticleApiResponse
import com.example.ign_app.helper.ArticleData
import com.example.ign_app.helper.PaginationScrollListener
import com.example.ign_app.networkcalls.IgnApi
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class ArticleTab : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var view1: View
    var contentId: String? = null
    var headline: String? = null
    var urlImage: String? = null
    var description: String? = null
    var authorName: String? = null
    var authorImage: String? = null
    var slug: String? = null
    var currentPage = PaginationScrollListener.PAGE_START
    private var isLastPage = false
    var isLoading = false
    private lateinit var adapter :ArticleAdapter
    var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            callingApi(currentPage)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_article_tab, container, false)
        recyclerView = view1.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = ArticleAdapter(context!!, ArrayList())
        recyclerView.adapter = adapter

//        recyclerView!!.addOnScrollListener(object : PaginationScrollListener(layoutManager!!) {
//            override fun loadMoreItems() {
//                isLoading = true
//                currentPage += 10
//                callingApi(currentPage)
//            }
//
//            override val isLastPage: Boolean = false
//
//            override var isLoading: Boolean = false
//        })

//        layoutManager = LinearLayoutManager(context)

//        adapter = ArticleAdapter(context!!, ArrayList())
//        recyclerView.setAdapter(adapter)
//        rv.adapter = HabitsAdapter(HabitDbTable(this).readAllHabits())

        return view
    }

    private fun callingApi(index: Int) {
        Handler().postDelayed({
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val ignApi = retrofit.create(IgnApi::class.java)
            Log.d("currentPage", (currentPage + 1).toString())
            val call = ignApi.getArticleJson(index, 10)
    call!!.enqueue(object : Callback<ArticleApiResponse> {
        override fun onResponse(call: Call<ArticleApiResponse>, response: Response<ArticleApiResponse>) {
            val articleDataArrayList = ArrayList<ArticleData>()
            isLoading = false
            Log.d(TAG, "onResponse Server Response: $response")
            Log.d(TAG, "onResponse received Information: " + response.body().toString())
            val dataArrayList = response.body()!!.data
            Log.d("BCSIZE", dataArrayList!!.size.toString())
            for (i in dataArrayList.indices) {
                Log.d(TAG, """
     onResposnse:
     contentId:${dataArrayList[i].contentId}
     Headline:${dataArrayList[i].metadata!!.headline}
     publishdate:${dataArrayList[i].metadata!!.publishDate}
     Description:${dataArrayList[i].metadata!!.description}
     Slug:https://ign.com/articles/${dataArrayList[i].metadata!!.slug}

     """.trimIndent())
                val thumbnailsArrayList = dataArrayList[i].thumbnails
                Log.d(TAG, """
     onResponse:
     url:${thumbnailsArrayList!![2].url}
     size:${thumbnailsArrayList[0].size}
     width:${thumbnailsArrayList[0].width}
     height${thumbnailsArrayList[0].height}

     """.trimIndent())
                val authorsArrayList = dataArrayList[i].authors
                Log.d(TAG, """
     onResponse:
     name:${authorsArrayList!![0].name}
     thumbnail${authorsArrayList[0].thumbnail}

     """.trimIndent())
                contentId = dataArrayList[i].contentId
                headline = dataArrayList[i].metadata!!.headline
                description = dataArrayList[i].metadata!!.description
                urlImage = thumbnailsArrayList[0].url
                authorName = authorsArrayList[0].name
                authorImage = authorsArrayList[0].thumbnail
                slug = "https://ign.com/articles/" + dataArrayList[i].metadata!!.slug
                val articleData = ArticleData(contentId!!, headline!!, description!!, urlImage!!, authorName!!, authorImage!!, slug!!)
                articleDataArrayList.add(articleData)
            }
            adapter.addItems(articleDataArrayList)
//                    recyclerView.adapter.
            if (index == 300) {
                isLastPage = true
            }
            isLoading = false
        }

        override fun onFailure(call: Call<ArticleApiResponse>, t: Throwable) {
            isLoading = false
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Not connected to Internet!")
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Retry") { dialogInterface, i -> callingApi(currentPage) }
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
        private const val BASE_URL = "https://ign-apis.herokuapp.com/"
        private const val TAG = "ArticleTaab"
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<ArticleApiResponse>) {

}
