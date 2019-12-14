package com.example.mycocktails.database

class CocktailDatabaseTest {
/*
    private lateinit var cocktailDao: CocktailDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: CocktailDatabase

    private lateinit var testCocktail: Cocktail
    private lateinit var testCategory: Category

    @Before
    fun createDb() {
        val context: Context = mockk()

        /* Dao moet in-memory -> zou verdwijnen indien process gestopt wordt */
        db = Room.inMemoryDatabaseBuilder(context, CocktailDatabase::class.java)
            /* Enkel toegestaan tijdens testen */
            .allowMainThreadQueries()
            .build()
        cocktailDao = db.cocktailDao
        categoryDao = db.categoryDao

        testCocktail = Cocktail("TestCocktailName", "category", "Instructions", "ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount","ingredient", "ingredientAmount", 1)
        testCategory = Category("TestCategoryName")
    }

    @After
    fun closeDb() {
        /* Db sluiten na test + mockks verwijderen */
        db.close()
        clearAllMocks()
    }

    @Test
    fun insertAndGetCocktail() {
        runBlocking {
            cocktailDao.insert(testCocktail)
            val cocktail = cocktailDao.getCocktailWithId(testCocktail.cocktailId)
            assertEquals(cocktail.name, testCocktail.name)
        }
    }

*/
}
