package edu.iesam.superheroes.features.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class FetchSuperHeroeUseCaseTest {

    @Test
    fun `should return list of students from repository`() {
        //Given
        val superHeroRepositoryMockk = mockk<SuperHeroeRepository>()
        val fetchSuperHeroUseCase = FetchSuperHeroeUseCase(studentRepositoryMockk)

        val student1 = SuperHeroe("1", "Marcos")
        val student2 = SuperHeroe("2", "María")
        val student3 = SuperHeroe("3", "Juan")
        val expectedStudents = listOf(student1, student2, student3)

        every { SuperHeroeRepository.fetch() } returns expectedStudents

        //When
        val result = fetchSuperHeroUseCase.fetch()

        //Then
        assertEquals(3, result.size)
        assertEquals(expectedStudents, result)
        assertTrue(result.contains(student1))
        assertTrue(result.contains(student2))
        assertTrue(result.contains(student3))
        verify(exactly = 1) { studentRepositoryMockk.fetch() }
    }

    @Test
    fun `should return empty list when no students exist`() {
        //Given
        val superHeroRepositoryMockk = mockk<SuperHeroeRepository>()
        val fetchStudentsUseCase = FetchStudentsUseCase(superHeroRepositoryMockk)

        every { superHeroRepositoryMockk.fetch() } returns emptyList()

        //When
        val result = fetchStudentsUseCase.fetch()

        //Then
        assertTrue(result.isEmpty())
        verify(exactly = 1) { superHeroRepositoryMockk.fetch() }
    }

}