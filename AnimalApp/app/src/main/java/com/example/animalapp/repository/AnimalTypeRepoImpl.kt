package com.example.animalapp.repository

import com.example.animalapp.api.ApiAiService
import com.example.animalapp.api.ApiService
import com.example.animalapp.model.AnimalSpecie
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.AnimalBreed
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.model.AnimalType
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.AuthResponse
import com.example.animalapp.model.GalleryDetail
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.SpecieGallery
import com.example.animalapp.model.TestModel
import com.example.animalapp.model.ImageResponse
import com.example.animalapp.model.SearchDetail
import com.example.animalapp.model.User
import com.example.animalapp.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.await
import javax.inject.Inject

class AnimalTypeRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiAiService: ApiAiService
) : AnimalTypeRepo {
    override suspend fun loginUser(email: String, pwd: String): Resource<AuthResponse> {
        return try {
            val result = apiService.loginUser(email, pwd).await()
            if (result.status)
                Resource.success(result)
            else
                Resource.error(result.message)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }

    }

    override suspend fun registerUser(
        name: String,
        email: String,
        pwd: String
    ): Resource<AuthResponse> {
        return try {
            val result = apiService.registerUser(name, email, pwd).await()
            if (result.status)
                Resource.success(result)
            else
                Resource.error(result.message)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getUser(token: String): Resource<User> {
        return try {
            val result = apiService.getUser("Bearer $token").await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getUserGallery(token: String): Resource<SpecieGallery> {
        return try {
            val result = apiService.getUserGallery("Bearer $token").await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getGalleryDetail(
        token: String,
        animal_specie_name: String
    ): Resource<GalleryDetail> {
        return try {
            val result = apiService.getGalleryDetail("Bearer $token", animal_specie_name).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun postImageToGallery(
        token: String,
        animal_specie_name: RequestBody,
        part: MultipartBody.Part
    ): Resource<ImageResponse> {
        return try {
            val result =
                apiService.postImageToGallery("Bearer $token", animal_specie_name, part).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun deleteImageGallery(token: String, image_id: Int): Resource<ImageResponse> {
        return try {
            val result =
                apiService.deleteImageGallery("Bearer $token", image_id).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }


    override suspend fun getAnimalType(): Resource<List<AnimalType>> {
        return try {
            val result = apiService.getAnimalType().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getAnimalSpecies(name: String): Resource<AnimalSpecie> {
        return try {
            val result = apiService.getAnimalSpecies(name).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getAnimalBreeds(id: Int): Resource<AnimalBreed> {
        return try {
            val result = apiService.getAnimalBreeds(id).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getMoreInfo(animalf_name: String): Resource<MoreInfo> {
        return try {
            val result = apiService.getMoreInfo(animalf_name).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getOtherResults(other_results: List<String>): Resource<AnimalSpecie> {
        return try {
            val result = apiService.getOtherResults(other_results).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getAnimalBreedDetail(id: Int): Resource<AnimalBreedItem> {
        return try {
            val result = apiService.getAnimalBreedDetail(id).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getMemoryCard(): Resource<MemoryCard> {
        return try {
            val result = apiService.getMemoryCard().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getQuizz(): Resource<Quizz> {
        return try {
            val result = apiService.getQuizz().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getTest(): Resource<TestModel> {
        return try {
            val result = apiAiService.getTest().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getFileName(part: MultipartBody.Part): Resource<TestModel> {
        return try {
            val result = apiAiService.getFileName(part).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getAnimalNamePre(part: MultipartBody.Part): Resource<AnimalPredictResult> {
        return try {
            val result = apiAiService.getAnimalNamePre(part).await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }

    override suspend fun getPrepareSearch(): Resource<SearchDetail> {
        return try {
            val result = apiService.getPrepareSearch().await()
            Resource.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.toString())
        }
    }
}