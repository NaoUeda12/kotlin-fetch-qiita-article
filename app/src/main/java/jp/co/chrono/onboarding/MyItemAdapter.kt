import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.chrono.onboarding.databinding.ListItemBinding

class MyItemAdapter(val itemList: MutableList<String>) :
    RecyclerView.Adapter<MyItemAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.itemText1.text = position.toString()
        holder.binding.itemText2.text = item
    }

    override fun getItemCount() = itemList.size
}