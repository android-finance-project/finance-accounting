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
import ru.rt.finance.R
import ru.rt.finance.databinding.FragmentDicExpenseEditBinding
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.Action
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.Event
import ru.rt.finance.features.dictonary.presentation.EditDicExpenseContract.State
import ru.rt.finance.features.dictonary.presentation.viewmodel.EditDicExpenseViewModel

class EditDicExpenseFragment : Fragment(R.layout.fragment_dic_expense_edit) {

    private val viewModel by viewModel<EditDicExpenseViewModel>()

    private lateinit var binding: FragmentDicExpenseEditBinding

    private lateinit var nameDicExpense: String
    private lateinit var idDicExpense: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDicExpenseEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameDicExpense = arguments?.getString("nameDicExpense").toString()
        idDicExpense = arguments?.getString("idDicExpense").toString()
        var nameDicExpense = nameDicExpense
        val idDicExpense = idDicExpense.toInt()
        binding.editDicExpenseName.setText(nameDicExpense)
        binding.textDicExpenseId.setText(idDicExpense.toString())
        binding.buttonSaveDicExpense.setOnClickListener {
            nameDicExpense = binding.editDicExpenseName.text.toString()
            viewModel.setEvent(
                Event.OnSaveDicExpenseClick(
                    nameDicExpense = nameDicExpense,
                    idDicExpense = idDicExpense
                )
            )
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
        findNavController().navigate(R.id.action_edit_dic_expense_to_navigation_dic_expense)

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
