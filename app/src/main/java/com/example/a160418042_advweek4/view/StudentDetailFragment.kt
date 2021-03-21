package com.example.a160418042_advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418042_advweek4.R
import com.example.a160418042_advweek4.util.loadImage
import com.example.a160418042_advweek4.viewmodel.DetailViewModel
import com.example.a160418042_advweek4.viewmodel.ListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            imageView2.loadImage(it.photoUrl,progressBar2)
            txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var student_id = ""
        arguments?.let {
                student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            //txtTurn.text = "Your score is $playerScore"
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(student_id)

        observeViewModel()
    }
}