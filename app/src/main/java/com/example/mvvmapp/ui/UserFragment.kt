
package com.example.mvvmapp.ui


import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.adapter.AppAdapter
import com.example.mvvmapp.data.entities.UserInfo
import com.example.mvvmapp.view_model.UserVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user.view.*
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment() {

    private val activityViewModel: UserVM by viewModels()

    private val recyclerViewAdapter by lazy {
        AppAdapter()
    }
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //bu kodu koymazsan search iconu toolbarda gözükmez.
        loadData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        view.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.saveEkraninaGecis)
        }

        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = view.user_rv
        recyclerView.adapter = recyclerViewAdapter

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        activityViewModel.loadData()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.loadData()
    }


    private fun loadData() {
        activityViewModel.userListInfo.observe(this) {
            recyclerViewAdapter.setData(it as ArrayList<UserInfo>)

        }

        activityViewModel.fail.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.imeOptions = (EditorInfo.IME_ACTION_DONE)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                activityViewModel.searchAfter(newText)
                activityViewModel.searchList.observe(this@UserFragment) {
                    recyclerViewAdapter.setData(it as ArrayList<UserInfo>)
                }
                return false
            }
        })
        activityViewModel.failure.observe(this) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }

    }


}