package com.dicoding.myrecyclerview.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.dicoding.myrecyclerview.R
import com.dicoding.myrecyclerview.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.apply {
            // Set the WebViewClient
            webview.apply {
                loadUrl(getString(R.string.default_url))
                settings.javaScriptEnabled = true
                addJavascriptInterface(WebAppInterface(requireContext()), "Android")
                webViewClient = MyWebViewClient(requireContext())
            }
            // Set the arrow back button
            arrowBack.setOnClickListener { if (webview.canGoBack()) webview.goBack() }
            // Set the arrow forward button
            arrowForward.setOnClickListener { if (webview.canGoForward()) webview.goForward() }
            // Implement the status of progress bar
            pageLoadStatus()
            // Implement the progressions of progress bar
            updateProgress()
            return root
        }
    }

    // Set the status of progress bar
    private fun pageLoadStatus() {
        binding.apply {
            webview.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressHorizontal.apply {
                        visibility = View.VISIBLE
                        progress = 0
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressHorizontal.visibility = View.GONE
                }
            }
        }
    }

    // Set the progressions of progress bar
    private fun updateProgress() {
        binding.apply {
            webview.webChromeClient = object: WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    progressHorizontal.progress = newProgress
                }
            }
        }
    }
}

class WebAppInterface(private val mContext: Context) {
    /** Show a toast from the web page
     * Ini aku baca bisa buat kalo webnya itu ngeluarin alert jadi bisa handle, tapi belom kucoba */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}

// Set the WebViewClient to prevent opening browser when click link with same host
class MyWebViewClient(private val context: Context) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (Uri.parse(request?.url.toString()).host == "covid19.go.id") {
            return false
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString()))
            startActivity(context, intent, null)
        }
        return true
    }
}