package ru.rt.finance.features.dictonary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rt.finance.databinding.FragmentDicExpenseAddBinding
import ru.rt.finance.R
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.Event
import ru.rt.finance.features.dictonary.presentation.AddDicExpenseContract.State
import ru.rt.finance.features.dictonary.presentation.viewmodel.AddDicExpenseViewModel

class AddDicExpenseFragment : Fragment(R.layout.fragment_dic_expense_add) {

    private val viewModel by viewModel<AddDicExpenseViewModel>()

    private lateinit var binding: FragmentDicExpenseAddBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDicExpenseAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSaveDicExpense.setOnClickListener {
            val nameDicExpense = binding.editDicExpenseName.text.toString()
            viewModel.setEvent(Event.OnSaveDicExpenseClick(nameDicExpense = nameDicExpense))
        }
        subscribeState()
        subscribeAction()
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
            is Action.DialogMessage -> {
                Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
            }
            is Action.DialogError -> {}
            is Action.NavigateToDicExpense -> navigateToDicExpense()

        }
    }

    private fun navigateToDicExpense() =
        findNavController().navigate(R.id.action_add_dic_expense_to_navigation_dic_expense)

    private fun handleState(state: State) {
        with(binding) {
            when (state) {
                is State.Initial -> {
                    progressBarLoad.isVisible = false
                    textError.isVisible = false
                }
                is State.Loading -> {
                    progressBarLoad.isVisible = true
                    textError.isVisible = false
                }
                is State.Error -> {
                    progressBarLoad.isVisible = false
                    textError.isVisible = true
                    textError.text = state.errorModel.message
                }
            }
        }
    }
}
