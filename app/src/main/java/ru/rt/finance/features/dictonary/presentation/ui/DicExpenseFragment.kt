package ru.rt.finance.features.dictonary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rt.finance.R
import ru.rt.finance.databinding.FragmentListDicExpenseBinding
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.Event
import ru.rt.finance.features.dictonary.presentation.DicExpenseContract.State
import ru.rt.finance.features.dictonary.presentation.listadapter.DicExpenseListAdapter
import ru.rt.finance.features.dictonary.presentation.viewmodel.DicExpenseViewModel

class DicExpenseFragment : Fragment(R.layout.fragment_list_dic_expense) {

    private val viewModel by viewModel<DicExpenseViewModel>()

    private lateinit var binding: FragmentListDicExpenseBinding
    private lateinit var dicExpenseListAdapter: DicExpenseListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListDicExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menu.clear()
                menuInflater.inflate(R.menu.top_nav_menu_dictonary, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when (menuItem.itemId) {
                    R.id.filter -> { //TODO:
                    }
                    R.id.sort -> { //TODO:
                        true
                    }
                    R.id.search -> { //TODO:
                    }
                    else -> {//TODO:
                    }
                }
                return true
            }
        }, viewLifecycleOwner)

        dicExpenseListAdapter = DicExpenseListAdapter() { position -> OnEditDicExpenseClick(position) }

        binding.recyclerViewDicExpense.adapter = dicExpenseListAdapter

        binding.buttonAddDicExpense.setOnClickListener {
            viewModel.setEvent(Event.OnAddDicExpenseClick)
        }
        subscribeState()
        subscribeAction()
        viewModel.setEvent(Event.OnViewReady)
    }

    fun navigateToAddDicExpense() {
        findNavController().navigate(R.id.action_dic_expense_to_add)
    }

    fun navigateToEditDicExpense() {
        findNavController().navigate(R.id.action_dic_expense_to_edit)
    }

    private fun OnEditDicExpenseClick(position: Int) {
        val nameDicExpense = dicExpenseListAdapter.currentList[position].nameDicExpenseEntity.toString()
        val idDicExpense = dicExpenseListAdapter.currentList[position].idDicExpenseEntity.toString()
        val bundle = bundleOf(
            "nameDicExpense" to nameDicExpense,
            "idDicExpense" to idDicExpense
        )
        findNavController().navigate(R.id.action_dic_expense_to_edit, bundle)
    }

    private fun subscribeState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { handleState(it) }
        }
    }

    private fun subscribeAction() {
        lifecycleScope.launch {
            viewModel.action.collect { handleAction(it) }
        }
    }

    private fun handleAction(action: Action) {
        when (action) {
            is Action.DialogMessage -> {}
            is Action.DialogError -> {}
            is Action.NavigateToAddDicExpense -> navigateToAddDicExpense()
            is Action.NavigateToEditDicExpense -> {
                navigateToEditDicExpense()
            }
        }
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Loading -> {
                binding.progressBarLoad.isVisible = true
                binding.buttonAddDicExpense.isVisible = false
                binding.textError.isVisible = false
            }
            is State.Error -> {
                binding.progressBarLoad.isVisible = false
                binding.recyclerViewDicExpense.isVisible = false
                binding.buttonAddDicExpense.isVisible = false
                binding.textError.isVisible = true
                binding.textError.text = state.errorModel.message
            }
            is State.Content -> {
                binding.progressBarLoad.isVisible = false
                binding.recyclerViewDicExpense.isVisible = true
                binding.buttonAddDicExpense.isVisible = true
                binding.textError.isVisible = false
                (binding.recyclerViewDicExpense.adapter as DicExpenseListAdapter).submitList(state.dicExpenses)
            }
        }
    }
}
