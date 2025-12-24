package com.example.daggerhiltmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.daggerhiltmvvm.db.FakerDAO
import com.example.daggerhiltmvvm.db.FakerDB
import com.example.daggerhiltmvvm.models.Product
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class FakeDAOTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    private lateinit var fakerDAO: FakerDAO

    @Inject
     lateinit var fakerDataBase: FakerDB


    @Before
    fun setUp(){
        hiltAndroidRule.inject()
        fakerDAO = fakerDataBase.getFakerDAO()

    }



    @Test
    fun insertProduct() = runTest{
        val product = Product(
            "",
            "",
            78,
            "",
            78.98,
            "pr1"
        )

        fakerDAO.addProducts(listOf(product))

        val result = fakerDAO.getProducts()
        Assert.assertEquals(1,result.size)
    }


}