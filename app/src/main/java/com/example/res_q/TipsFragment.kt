package com.example.res_q

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.res_q.databinding.ActivityHomeBinding
import com.example.res_q.databinding.FragmentTipsBinding

class TipsFragment  : AppCompatActivity() {

    private lateinit var binding: FragmentTipsBinding
    private lateinit var btn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = FragmentTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.telf1.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_DIAL)
            intent1.data = Uri.parse("tel:" + 113)
            startActivity(intent1)
        }
        binding.telf2.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_DIAL)
            intent2.data = Uri.parse("tel:" + 110)
            startActivity(intent2)
        }
        binding.telf3.setOnClickListener {
            val intent3 = Intent(Intent.ACTION_DIAL)
            intent3.data = Uri.parse("tel:" + 115)
            startActivity(intent3)
        }
        binding.telf4.setOnClickListener {
            val intent4 = Intent(Intent.ACTION_DIAL)
            intent4.data = Uri.parse("tel:" + 129)
            startActivity(intent4)
        }
        binding.telf5.setOnClickListener {
            val intent5 = Intent(Intent.ACTION_DIAL)
            intent5.data = Uri.parse("tel:" + 123)
            startActivity(intent5)
        }
    }
}
//        binding = FragmentTipsBinding.inflate(layoutInflater)
////        binding.imageButton.setOnClickListener {
////            findNavController().navigate(ArticleFragmentDirections.actionArticleFragmentToHomepageFragment())
////        }
//
////        fetchNews()
//
//        return binding.root
//    }
//
////    private fun fetchNews() {
////        newsApiClient.getEverything(
////            EverythingRequest.Builder()
////                .q("Mental Health OR Depression OR Anxiety OR anxiety OR depression")
////                .language("id")
////                .build(),
////            object: NewsApiClient.ArticlesResponseCallback {
////                override fun onSuccess(response: ArticleResponse?) {
////                    response?.articles?.forEach {
////                        newsItem.add(it)
////                    }
////                    bindNews()
////                }
////
////                override fun onFailure(throwable: Throwable?) {
////                    throwable?.message?.let {
////                        Log.e("Error", it)
////                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
////                    }
////                }
////            }
////        )
////    }
//
////    private fun bindNews() {
////        val adapter = NewsAdapter(newsItem, OnNewsClickListener {
////            val uri = Uri.parse(it)
////            startActivity(Intent(Intent.ACTION_VIEW, uri))
////        })
////        binding.rvBerita.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
////        binding.rvBerita.adapter = adapter
////    }
//}