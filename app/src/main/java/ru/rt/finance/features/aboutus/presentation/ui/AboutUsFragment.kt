package ru.rt.finance.features.aboutus.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rt.finance.R
import ru.rt.finance.databinding.FragmentAboutUsBinding
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.Action
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.Event
import ru.rt.finance.features.aboutus.presentation.AboutUsContract.State
import ru.rt.finance.features.aboutus.presentation.listadapter.AboutUsListAdapter
import ru.rt.finance.features.aboutus.presentation.viewmodel.AboutUsViewModel

class AboutUsFragment : Fragment() {

    private val viewModel by viewModel<AboutUsViewModel>()

    private lateinit var binding: FragmentAboutUsBinding
    private lateinit var aboutUsListAdapter: AboutUsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutUsListAdapter = AboutUsListAdapter()

        binding.recyclerViewAboutUs.adapter = aboutUsListAdapter

        binding.buttonCloseAboutUs.setOnClickListener {
            viewModel.setEvent(Event.OnCloseAboutUsClick)
        }
        subscribeState()
        subscribeAction()
        viewModel.setEvent(Event.OnViewReady)
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
            is Action.NavigateToMainFragment -> {
                navigateToMainFragment()
            }
        }
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Content -> {
                binding.textError.isVisible = false
                (binding.recyclerViewAboutUs.adapter as AboutUsListAdapter).submitList(state.aboutUsModel)
            }
            is State.Error -> {
                binding.textError.isVisible = true
                binding.textError.text = state.errorModel.message
            }
            else -> {
                //TODO:By ginger - also need always else
                binding.textError.isVisible = true
                binding.textError.text = "Ошибка не идентифицированна - обратитесь к разработчикам."
            }
        }
    }

    fun navigateToMainFragment() {
        findNavController().navigate(R.id.action_about_us_to_main_fragment)
    }
}
