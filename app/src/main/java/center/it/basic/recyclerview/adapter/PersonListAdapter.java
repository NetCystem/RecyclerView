package center.it.basic.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import center.it.basic.recyclerview.R;
import center.it.basic.recyclerview.model.Person;

/**
 * Created by yervand on 12/21/17.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {

    private List<Person> personList;
    private Context context;

    public PersonListAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent);

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Person person=personList.get(position);
            holder.surname.setText(person.getName());
            holder.name.setText(person.getSurname());
            Glide.with(context)
                    .load(person.getImgUrl())
                    .apply(new RequestOptions().error(R.drawable.ic_launcher_background).circleCrop())
                    .into(holder.profileImage);
            changeColor(person.isSex(),holder.name,holder.surname);
    }

    @Override
    public int getItemCount() {
        return personList.isEmpty()?0:personList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView profileImage;
        private TextView name;
        private TextView surname;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImage=itemView.findViewById(R.id.profile_image);
            name=itemView.findViewById(R.id.name);
            surname=itemView.findViewById(R.id.surname);
        }
    }
    private void changeColor(boolean gender,TextView... view){
        if (!gender){
            view[0].setTextColor(context.getResources().getColor(R.color.female_color));
            view[1].setTextColor(context.getResources().getColor(R.color.female_color));
        }
    }
}
