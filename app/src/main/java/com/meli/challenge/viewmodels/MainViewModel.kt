package com.meli.challenge.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.meli.challenge.api.ApiRepository
import com.meli.challenge.models.Results
import com.meli.challenge.utils.Resource
import androidx.hilt.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                         repository: ApiRepository,
                                         application: Application,
                                         )
    : AndroidViewModel(application) {

    val response: LiveData<Resource<Results>> =
        repository.getSearch(
            savedStateHandle.get<String>("query")
                ?: throw Exception("Se necesita query para la busqueda")
        )
}


