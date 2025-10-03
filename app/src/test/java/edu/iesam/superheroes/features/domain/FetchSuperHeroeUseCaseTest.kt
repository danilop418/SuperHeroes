package edu.iesam.superheroes.features.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class FetchSuperHeroeUseCaseTest {

    @Test
    fun `when repository return succes then usecase returns succes`() {
        //Given
        val superheroeRepositoryMockk = mockk<SuperHeroeRepository>()
        val expectedHeroes = listOf(
            SuperHeroe("1", "Solaris", "solaris", "https://example.com/images/solaris.jpg"),
            SuperHeroe(
                "2",
                "Aquamancer",
                "aquamancer",
                "https://example.com/images/aquamancer.jpg"
            ),
            SuperHeroe(
                "3",
                "Voltstrike",
                "voltstrike",
                "https://example.com/images/voltstrike.jpg"
            ),
            SuperHeroe(
                "4",
                "Shadowlynx",
                "shadowlynx",
                "https://example.com/images/shadowlynx.jpg"
            ),
        )

        every { superheroeRepositoryMockk.fetch() } returns Result.success(expectedHeroes)

        val superHeroeUseCase = FetchSuperHeroeUseCase(superheroeRepositoryMockk)

        // When
        val result = superHeroeUseCase.fetch()

        //Then
        assertTrue(result.isSuccess)
        assertEquals(expectedHeroes, result.getOrNull())
        verify(exactly = 1) { superheroeRepositoryMockk.fetch() }
    }

    @Test
    fun `when repository returns internet error then usecase returns failure`() {
        // Given
        val superheroeRepositoryMockk = mockk<SuperHeroeRepository>()
        every { superheroeRepositoryMockk.fetch() } returns Result.failure(ErrorApp.InternetConexionError)

        val superHeroeUseCase = FetchSuperHeroeUseCase(superheroeRepositoryMockk)
        // When
        val result = superHeroeUseCase.fetch()

        // Then
        assertTrue(result.isFailure)
        assertEquals(ErrorApp.InternetConexionError, result.exceptionOrNull())
        verify(exactly = 1) { superheroeRepositoryMockk.fetch() }
    }

    @Test
    fun `when repository returns server error then usecase returns server error`() {
        // Given
        val superheroeRepositoryMockk = mockk<SuperHeroeRepository>()
        every { superheroeRepositoryMockk.fetch() } returns Result.failure(ErrorApp.ServerErrorApp)

        val superHeroeUseCase = FetchSuperHeroeUseCase(superheroeRepositoryMockk)

        // When
        val result = superHeroeUseCase.fetch()

        // Then
        assertTrue(result.isFailure)
        assertEquals(ErrorApp.ServerErrorApp, result.exceptionOrNull())
        verify(exactly = 1) { superheroeRepositoryMockk.fetch() }
    }

}