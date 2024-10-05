package com.afrikalabs.songapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.afrikalabs.songapp.data.database.SongDatabase
import com.afrikalabs.songapp.data.model.SongEntity
import com.afrikalabs.songapp.data.repository.SongRepositoryImpl
import com.afrikalabs.songapp.domain.repository.SongRepository
import com.afrikalabs.songapp.domain.usecase.GetAllSongsUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByAuthorUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByContentUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByTitleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): SongDatabase {
        return Room.databaseBuilder(
            context,
            SongDatabase::class.java,
            "song_db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(Room.databaseBuilder(context, SongDatabase::class.java, "song_db").build())
                }
            }
        })
            .build()
    }

    @Provides
    @Singleton
    fun provideSongRepository(db: SongDatabase): SongRepository {
        return SongRepositoryImpl(db.songDao())
    }

    @Provides
    fun provideGetAllSongsUseCase(repository: SongRepository) = GetAllSongsUseCase(repository)

    @Provides
    fun provideSearchSongByTitleUseCase(repository: SongRepository) = SearchSongByTitleUseCase(repository)

    @Provides
    fun provideSearchSongByContentUseCase(repository: SongRepository) = SearchSongByContentUseCase(repository)

    @Provides
    fun provideSearchSongByAuthorUseCase(repository: SongRepository) = SearchSongByAuthorUseCase(repository)

    // Fonction pour remplir la base de données
    private suspend fun populateDatabase(database: SongDatabase) {
        val songDao = database.songDao()

        val songList = listOf(
            SongEntity(1, "Amazing Grace", "John Newton", "Amazing grace, how sweet the sound, That saved a wretch like me..."),
            SongEntity(2, "How Great Thou Art", "Carl Boberg", "O Lord my God, when I in awesome wonder, Consider all the works thy hands have made..."),
            SongEntity(3, "Blessed Assurance", "Fanny Crosby", "Blessed assurance, Jesus is mine! Oh, what a foretaste of glory divine!..."),
            SongEntity(4, "I Surrender All", "Judson W. Van DeVenter", "All to Jesus I surrender, All to Him I freely give..."),
            SongEntity(5, "It Is Well With My Soul", "Horatio Spafford", "When peace like a river attendeth my way, When sorrows like sea billows roll..."),
            SongEntity(6, "Just As I Am", "Charlotte Elliott", "Just as I am, without one plea, But that Thy blood was shed for me..."),
            SongEntity(7, "The Old Rugged Cross", "George Bennard", "On a hill far away stood an old rugged cross, The emblem of suffering and shame..."),
            SongEntity(8, "Great Is Thy Faithfulness", "Thomas Chisholm", "Great is Thy faithfulness, O God my Father, There is no shadow of turning with Thee..."),
            SongEntity(9, "What a Friend We Have in Jesus", "Joseph Scriven", "What a friend we have in Jesus, All our sins and griefs to bear..."),
            SongEntity(10, "Jesus Paid It All", "Elvina M. Hall", "I hear the Savior say, Thy strength indeed is small, Child of weakness, watch and pray..."),
            SongEntity(11, "Shall We Gather at the River", "Robert Lowry", "Shall we gather at the river, Where bright angel feet have trod..."),
            SongEntity(12, "To God Be the Glory", "Fanny Crosby", "To God be the glory, great things He hath done, So loved He the world that He gave us His Son..."),
            SongEntity(13, "Victory in Jesus", "Eugene Monroe Bartlett", "I heard an old, old story, How a Savior came from glory..."),
            SongEntity(14, "Standing on the Promises", "Russell Kelso Carter", "Standing on the promises of Christ my King, Through eternal ages let His praises ring..."),
            SongEntity(15, "When We All Get to Heaven", "Eliza Edmunds Hewitt", "Sing the wondrous love of Jesus, Sing His mercy and His grace..."),
            SongEntity(16, "Because He Lives", "Bill Gaither", "God sent His Son, they called Him Jesus, He came to love, heal, and forgive..."),
            SongEntity(17, "He Lives", "Alfred Henry Ackley", "I serve a risen Savior, He's in the world today..."),
            SongEntity(18, "Turn Your Eyes Upon Jesus", "Helen Howarth Lemmel", "O soul, are you weary and troubled? No light in the darkness you see..."),
            SongEntity(19, "Rock of Ages", "Augustus M. Toplady", "Rock of Ages, cleft for me, Let me hide myself in Thee..."),
            SongEntity(20, "Leaning on the Everlasting Arms", "Elisha Hoffman", "What a fellowship, what a joy divine, Leaning on the everlasting arms..."),
            SongEntity(21, "I'll Fly Away", "Albert E. Brumley", "Some glad morning when this life is o'er, I'll fly away..."),
            SongEntity(22, "We Are Climbing Jacob's Ladder", "Traditional", "We are climbing Jacob's ladder, We are climbing Jacob's ladder..."),
            SongEntity(23, "Swing Low, Sweet Chariot", "Traditional", "Swing low, sweet chariot, Coming for to carry me home..."),
            SongEntity(24, "Soon and Very Soon", "Andraé Crouch", "Soon and very soon, We are going to see the King..."),
            SongEntity(25, "Go Tell It on the Mountain", "John W. Work Jr.", "Go tell it on the mountain, Over the hills and everywhere..."),
            SongEntity(26, "Down by the Riverside", "Traditional", "I'm gonna lay down my burden, Down by the riverside..."),
            SongEntity(27, "Oh, Happy Day", "Edwin Hawkins", "Oh, happy day, When Jesus washed, When Jesus washed my sins away..."),
            SongEntity(28, "Precious Lord, Take My Hand", "Thomas A. Dorsey", "Precious Lord, take my hand, Lead me on, let me stand..."),
            SongEntity(29, "His Eye Is on the Sparrow", "Civilla D. Martin", "Why should I feel discouraged, Why should the shadows come..."),
            SongEntity(30, "Kumbaya", "Traditional", "Kumbaya, my Lord, kumbaya, Kumbaya, my Lord, kumbaya..."),
            SongEntity(31, "Pass Me Not, O Gentle Savior", "Fanny Crosby", "Pass me not, O gentle Savior, Hear my humble cry..."),
            SongEntity(32, "Come Thou Fount of Every Blessing", "Robert Robinson", "Come Thou fount of every blessing, Tune my heart to sing Thy grace..."),
            SongEntity(33, "There Is Power in the Blood", "Lewis Edgar Jones", "Would you be free from the burden of sin? There's power in the blood..."),
            SongEntity(34, "I Have Decided to Follow Jesus", "Traditional", "I have decided to follow Jesus, No turning back, no turning back..."),
            SongEntity(35, "Praise to the Lord, the Almighty", "Joachim Neander", "Praise to the Lord, the Almighty, the King of creation..."),
            SongEntity(36, "This Little Light of Mine", "Harry Dixon Loes", "This little light of mine, I'm gonna let it shine..."),
            SongEntity(37, "Oh How I Love Jesus", "Frederick Whitfield", "There is a name I love to hear, I love to sing its worth..."),
            SongEntity(38, "I Need Thee Every Hour", "Annie S. Hawks", "I need Thee every hour, Most gracious Lord..."),
            SongEntity(39, "Come, Ye Sinners, Poor and Needy", "Joseph Hart", "Come, ye sinners, poor and needy, Weak and wounded, sick and sore..."),
            SongEntity(40, "Tis So Sweet to Trust in Jesus", "Louisa M. R. Stead", "'Tis so sweet to trust in Jesus, Just to take Him at His word..."),
            SongEntity(41, "He's Got the Whole World in His Hands", "Traditional", "He's got the whole world in His hands, He's got the whole world in His hands..."),
            SongEntity(42, "In the Garden", "C. Austin Miles", "I come to the garden alone, While the dew is still on the roses..."),
            SongEntity(43, "I Love to Tell the Story", "Katherine Hankey", "I love to tell the story, 'Twill be my theme in glory..."),
            SongEntity(44, "Holy, Holy, Holy", "Reginald Heber", "Holy, holy, holy! Lord God Almighty, Early in the morning our song shall rise to Thee..."),
            SongEntity(45, "Crown Him with Many Crowns", "Matthew Bridges", "Crown Him with many crowns, The Lamb upon His throne..."),
            SongEntity(46, "Fairest Lord Jesus", "Anonymous", "Fairest Lord Jesus, Ruler of all nature, O Thou of God and man the Son..."),
            SongEntity(47, "All Hail the Power of Jesus' Name", "Edward Perronet", "All hail the power of Jesus' name, Let angels prostrate fall..."),
            SongEntity(48, "Abide with Me", "Henry Francis Lyte", "Abide with me, fast falls the eventide, The darkness deepens; Lord, with me abide..."),
            SongEntity(49, "Love Lifted Me", "James Rowe", "I was sinking deep in sin, far from the peaceful shore..."),
            SongEntity(50, "Revive Us Again", "William P. Mackay", "We praise Thee, O God, for the Son of Thy love, For Jesus who died and is now gone above...")
        )
        songDao.insertAll(songList) // Insertion des chansons dans la base de données
    }
}