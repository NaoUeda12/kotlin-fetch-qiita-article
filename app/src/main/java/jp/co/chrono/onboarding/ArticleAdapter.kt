package jp.co.chrono.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.chrono.onboarding.databinding.ListItemBinding
import jp.co.chrono.onboarding.model.QiitaArticle

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var articles: List<QiitaArticle> = emptyList()

    // ViewHolder
    class ArticleViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.itemText1.text = article.title
        holder.binding.itemText2.text = article.user?.name ?: "Unknown Author"
    }

    override fun getItemCount(): Int = articles.size

    // データ更新用メソッド
    fun submitList(newArticles: List<QiitaArticle>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
