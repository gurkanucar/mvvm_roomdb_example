package com.gusoft.mvvm_roomdb_example.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.gusoft.mvvm_roomdb_example.R
import com.gusoft.mvvm_roomdb_example.SharedViewModel
import com.gusoft.mvvm_roomdb_example.databinding.FragmentHomeBinding
import com.gusoft.mvvm_roomdb_example.db.models.Person
import com.gusoft.mvvm_roomdb_example.db.viewmodel.PersonViewModel
import com.gusoft.mvvm_roomdb_example.fragments.home.adapter.PersonListAdapter
import com.gusoft.mvvm_roomdb_example.utils.hideKeyboard


class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private val mToDoViewModel: PersonViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: PersonListAdapter by lazy { PersonListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup RecyclerView
        setupRecyclerview()


        // Observe LiveData
        mToDoViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter.setData(data)
//            binding.personRecyclerView.scheduleLayoutAnimation()
        }


        // Hide soft keyboard
        hideKeyboard(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val menuHost: MenuHost = requireActivity()
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.list_fragment_menu, menu)
//
//                val search = menu.findItem(R.id.menu_search)
//                val searchView = search.actionView as? SearchView
//                searchView?.isSubmitButtonEnabled = true
//                searchView?.setOnQueryTextListener(this@ListFragment)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                when (menuItem.itemId) {
//                    R.id.menu_delete_all -> confirmRemoval()
//                    R.id.menu_priority_high ->
//                        mToDoViewModel.sortByHighPriority.observe(viewLifecycleOwner) {
//                            adapter.setData(it)
//                        }
//                    R.id.menu_priority_low ->
//                        mToDoViewModel.sortByLowPriority.observe(viewLifecycleOwner) {
//                            adapter.setData(it)
//                        }
//                    android.R.id.home -> requireActivity().onBackPressed()
//                }
//                return true
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun setupRecyclerview() {
        val myRecyclerView = binding.personRecyclerView
        myRecyclerView.adapter = adapter
        //recyclerView.layoutManager =
        //   StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Swipe to Delete
        swipeToDelete(myRecyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                // Delete Item
                mToDoViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                // Restore Deleted Item
                restoreDeletedData(viewHolder.itemView, deletedItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view: View, deletedItem: Person) {
        val snackBar = Snackbar.make(
            view, "Deleted '${deletedItem.name}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            mToDoViewModel.insertData(deletedItem)
        }
        snackBar.show()
    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if (query != null) {
//            searchThroughDatabase(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(query: String?): Boolean {
//        if (query != null) {
//            searchThroughDatabase(query)
//        }
//        return true
//    }

//    private fun searchThroughDatabase(query: String) {
//        val searchQuery = "%$query%"
//
//        mToDoViewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner) { list ->
//            list?.let {
//                Log.d("ListFragment", "searchThroughDatabase")
//                adapter.setData(it)
//            }
//        }
//    }
//
//    // Show AlertDialog to Confirm Removal of All Items from Database Table
//    private fun confirmRemoval() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Yes") { _, _ ->
//            mToDoViewModel.deleteAll()
//            Toast.makeText(
//                requireContext(),
//                "Successfully Removed Everything!",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        builder.setNegativeButton("No") { _, _ -> }
//        builder.setTitle("Delete everything?")
//        builder.setMessage("Are you sure you want to remove everything?")
//        builder.create().show()
//    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}