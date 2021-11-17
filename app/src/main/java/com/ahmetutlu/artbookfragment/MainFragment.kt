package com.ahmetutlu.artbookfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ahmetutlu.artbookfragment.R
import com.ahmetutlu.artbookfragment.adapter.ArtAdapter
import com.ahmetutlu.artbookfragment.databinding.FragmentArtBinding
import com.ahmetutlu.artbookfragment.model.Art
import com.ahmetutlu.artbookfragment.roomdb.ArtDao
import com.ahmetutlu.artbookfragment.roomdb.ArtDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers




class MainFragment : Fragment() {

    private var _binding: FragmentArtBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var db: ArtDatabase
    private lateinit var artDao: ArtDao
    val compositeDisposable= CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db= Room.databaseBuilder(requireActivity(),ArtDatabase::class.java,"Arts").build()
        artDao=db.ArtDao()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //şuan mainActivye verileri çekiiyoruz
        compositeDisposable.add(
                artDao.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse)
        )
    }
    private fun handleResponse(artList: List<Art>){//yukarda çekilen veriler burdaki listede döndürülecek

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }






}