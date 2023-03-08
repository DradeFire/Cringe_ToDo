package com.cringeteam.todoproject.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.cringeteam.todoproject.common.logger.Logger
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<B: ViewBinding, VM: BaseViewModel> : Fragment() {

    protected abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
    private var _binding: B? = null
    protected val binding = _binding

    private var _viewModel: VM? = null
    protected val viewModel = _viewModel
    protected abstract val viewModelClass: Class<VM>

    protected abstract val screenName: String

    private var _compositeDisposable: CompositeDisposable? = null
    protected val compositeDisposable = _compositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = inflater(inflater, container, false)
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (_viewModel == null) {
            _viewModel = ViewModelProvider(requireActivity())[viewModelClass]

            initStartValues()
            initUI()
            initRecycler()
            initObservers()
            initButtons()
        }
    }

    /**
     * При реалезации ОБЯЗАТЕЛЬНО вызывать в начале super.initUI()
     */
    protected open fun initUI() {
        Logger.log("initUI -> $screenName")
    }

    /**
     * При реалезации ОБЯЗАТЕЛЬНО вызывать в начале super.initObservers()
     */
    protected open fun initObservers() {
        Logger.log("initObservers -> $screenName")
    }

    /**
     * При реалезации ОБЯЗАТЕЛЬНО вызывать в начале super.initStartValues()
     */
    protected open fun initStartValues() {
        Logger.log("initStartValues -> $screenName")
    }

    /**
     * При реалезации ОБЯЗАТЕЛЬНО вызывать в начале super.initButtons()
     */
    protected open fun initButtons() {
        Logger.log("initButtons -> $screenName")
    }

    /**
     * При реалезации ОБЯЗАТЕЛЬНО вызывать в начале super.initRecycler()
     */
    protected open fun initRecycler() {
        Logger.log("initRecycler -> $screenName")
    }

    override fun onDetach() {
        _binding = null
        _viewModel = null
        _compositeDisposable?.dispose()
        _compositeDisposable?.clear()
        _compositeDisposable = null
        super.onDetach()
    }

}
