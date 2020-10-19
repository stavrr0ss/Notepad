package com.atoming.notepadkotlin.ui

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atoming.notepadkotlin.R
import com.atoming.notepadkotlin.models.DbObject
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.viewmodels.AddLinkFactoryModel
import com.atoming.notepadkotlin.viewmodels.AddLinkViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_link_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddLinkFragment : Fragment() {

    private val LOG_TAG = AddLinkFragment::class.simpleName
    private lateinit var editLink: TextInputEditText
    private lateinit var titleText: TextView
    private lateinit var imageLink: ImageView
    private lateinit var descriptionText: TextView
    private lateinit var linkUrl: TextView
    private lateinit var linkCard: CardView
    private lateinit var progress: ProgressBar
    private lateinit var fab: FloatingActionButton

    private var url: String = ""

    private var metaResponse: MetaResponse = MetaResponse()
    private lateinit var linkViewModel: AddLinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.add_link_layout, container, false).apply {
            //initializing views here
            editLink = findViewById(R.id.edit_link)
            titleText = findViewById(R.id.link_title)
            imageLink = findViewById(R.id.image_link)
            descriptionText = findViewById(R.id.link_description)
            linkUrl = findViewById(R.id.link_urlText)
            linkCard = findViewById(R.id.link_card)
            progress = findViewById(R.id.progress_circular)
            fab = findViewById(R.id.floatingActionButton)
        }
        val urlArg: String? = arguments?.let { AddLinkFragmentArgs.fromBundle(it).urlTextArguments }
        if (urlArg != null) {
            val factory = AddLinkFactoryModel(activity!!.application, urlArg)
            linkViewModel = factory.create(AddLinkViewModel::class.java)
            editLink.setText(urlArg)
            linkCard.visibility = View.VISIBLE
            linkViewModel.getResponse().observe(viewLifecycleOwner, {
                setValues(it)
                metaResponse = it
            })
        }

        editLink.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                url = editLink.text.toString()
                progress.visibility = View.VISIBLE

            }

            override fun afterTextChanged(p0: Editable?) {
                url = editLink.text.toString()
                if (URLUtil.isValidUrl(url)) {
                    if (urlArg == null) { // check if url comes from outside app or pasted in edit field
                        progress.visibility = View.GONE
                        linkCard.visibility = View.VISIBLE
                        val factory = AddLinkFactoryModel(activity!!.application, url)
                        linkViewModel = factory.create(AddLinkViewModel::class.java)
                        linkViewModel.getResponse().observe(viewLifecycleOwner, {
                            setValues(it)
                            metaResponse = it
                        })
                    } else {
                        linkViewModel.getResponse().observe(viewLifecycleOwner, {
                            setValues(it)
                        })
                    }
                } else {
                    Toast.makeText(activity, "Not a valid URL!", Toast.LENGTH_SHORT).show()
                }
            }
        })
        fab.setOnClickListener {
            var metaList: MutableList<MetaResponse> = mutableListOf<MetaResponse>()
            var response = MetaResponse(
                metaResponse.title,
                metaResponse.image,
                metaResponse.description,
                metaResponse.urlLink,
                metaResponse.siteName
            )
            metaList.add(response)
            val dbObject = DbObject(0, null, null, metaList)
            if (metaResponse.description.isEmpty()) {
                //something hapens here
            } else {
                linkViewModel.insertNote(dbObject)
            }
            this.findNavController().navigate(R.id.action_addLinkFragment_to_allNotesFragment)
        }
        return v
    }

    fun setValues(metaResponse: MetaResponse) {
        //fetchDocument(url)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            metaResponse.description.let {
                descriptionText.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
                Log.d(LOG_TAG, "DESCRIPTION IS $it")
            }
            metaResponse.title.let {
                titleText.text = it
            }
            val imageUrl = metaResponse.image
            if (!imageUrl.isEmpty()) {
                imageLink.visibility = View.VISIBLE
                Picasso.get().load(imageUrl).into(imageLink)
                //Glide.with(this).load(imageUrl).into(imageLink)
            }
            linkUrl.text = metaResponse.urlLink
        }
    }
}