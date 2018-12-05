
import org.junit.jupiter.api.Assertions
import java.io.IOException
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import io.qameta.allure.okhttp3.AllureOkHttp3
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import retrofit2.Call


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Retrofit tests")
internal class RetrofitTests {
    private var service: GitHubService? = null

    @BeforeAll
    fun setUp() {
        val allureOkHttp3 = AllureOkHttp3()
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(allureOkHttp3)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(GitHubService::class.java)
    }

    @Test
    @DisplayName("Able to get github repository list by name")
    @Throws(IOException::class)
    fun gitHubRepoTest() {
        val reposCall: Call<List<Repo>> = service!!.listRepos("iljapavlovs")
        val repos: List<Repo>? = reposCall.execute().body()


        for (repo in repos!!){
            println(repo.name)
        }

        Assertions.assertNotNull(repos)
    }
}