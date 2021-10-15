package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post


class PostRepositoryInMemoryImplementation : PostRepository {

    private var postId = 1L
    private var posts = listOf(
        Post(
            id = postId++,
            likes = 0L,
            shares = 0L,
            views = 0L,
            author = "Нетология",
            published = "31 сентября в 13:37",
            content = "Жена посылает мужа—программиста в магазин:   — Купи батон колбасы. Да, и спроси, есть ли яйца. Если есть — возьми десяток.   Программист приходит в магазин:   — Батон колбасы, пожалуйста. Ага, спасибо. А яйца у вас в продаже есть?   — Есть.   — Тогда, пожалуйста, ещё девять батонов колбасы. "
        ),
        Post(
            id = postId++,
            likes = 1L,
            shares = 1L,
            views = 1L,
            author = "Нетология",
            published = "30 сентября в 13:37",
            content = "Встречаются два бывших одноклассника. Один — новый русский, а другой — программист. Первый спрашивает второго: — Ну что, братан, как дела? — Да вот, уже почти год сижу на Яве, пишу всякие приложения. — Ну ты крут! Впрочем, я тоже в этом году на Кипре две недели пробыл. "
        ),
        Post(
            id = postId++,
            likes = 999999L,
            shares = 92111L,
            views = 10000L,
            author = "Нетология",
            published = "29 сентября в 13:37",
            content = "Перепись населения у программиста: — Ваш родной язык. — Как это родной язык? — Ну какой Вы язык с детства изучали, всю жизнь использовали? — Basic. — Да нет, настоящий. — А! Настоящий! Тогда Си. "
        ),
        Post(
            id = postId++,
            likes = 99999999L,
            shares = 92423L,
            views = 1254L,
            author = "Нетология",
            published = "28 сентября в 13:37",
            content = "Один программист другому: — Вот представь:  — У тебя есть 1000 рублей... Или, для круглого счета, пусть у тебя 1024. "
        ),
        Post(
            id = postId++,
            likes = 999999L,
            shares = 23L,
            views = 125L,
            author = "Нетология",
            published = "25 сентября в 13:37",
            content = "Сидит программист в баре, пьет пиво. К нему подходит девица: — Если хочешь хорошо отдохнуть сегодня, то меня зовут Бетти... — А если я не хочу сегодня хорошо отдохнуть, то как тебя зовут? "
        ),
        Post(
            id = postId++,
            likes = 99999L,
            shares = 9L,
            views = 125332644L,
            author = "Нетология",
            published = "27 сентября в 13:37",
            content = "Приходит студент—программист на занятия с утра злой. Его одногрупники спрашивают: — Ты чего такой злой? — Да программу вчера всю ночь набивал. — И что, не заработала? — Да нет, заработала. — Может, неправильно заработала? — Да нет, правильно. — А что тогда? — Да на Backspace уснул…"
        ),
        Post(
            id = postId++,
            likes = 9999L,
            shares = 1235577L,
            views = 111111111L,
            author = "Нетология",
            published = "26 сентября в 13:37",
            content = "Приходят к программисту гости, а у него шум, гам. В общем, ссорятся между собой его дети. Гости спрашивают программера: — А чего у тебя дети так орут? А он им в ответ, оторвавшись от компа: — Конфликт версий."
        ),
        Post(
            id = postId++,
            likes = 999L,
            shares = 92L,
            views = 1253644L,
            author = "Нетология",
            published = "25 сентября в 13:37",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )
    ).reversed()
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                if (!it.likedByMe) {
                    it.copy(likedByMe = !it.likedByMe, likes = it.likes + 1)
                } else {
                    it.copy(likedByMe = !it.likedByMe, likes = it.likes - 1)
                }
            } else it
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(shares = it.shares + 1) else it
        }
        data.value = posts
    }

    override fun removeByID(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = postId++,
                author = "Me",
                likedByMe = false,
                published = "now")
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        data.value = posts
    }
}