package nl.hva.madlevel5.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.hva.madlevel5.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure?) {
        this.failure.value = failure
    }

}