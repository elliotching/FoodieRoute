package fcsit.foodieroute;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Elliot on 19-Aug-16.
 */
public class ElliotListViewActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<ElliotObject> mDataArrayList;

    public ElliotListViewActivityAdapter(Context c, ArrayList<ElliotObject> data) {
        mContext = c;
        mDataArrayList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_view_item_layout, parent, false);
        ElliotHolder h = new ElliotHolder(view);
        return new ElliotHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ElliotHolder elliotHolder = (ElliotHolder) holder;
        elliotHolder.thisTextView = (TextView) elliotHolder.thisItemView.findViewById(R.id.m_list_view_item_text_view);
        elliotHolder.thisI = (ImageView) elliotHolder.thisItemView.findViewById(R.id.image);
        elliotHolder.thisTextView.setText(mDataArrayList.get(position).s);
//        elliotHolder.thisTextView.setBackgroundResource(mDataArrayList.get(position).colorID);
        elliotHolder.thisI.setImageResource(mDataArrayList.get(position).photoRes);
    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    private class ElliotHolder extends RecyclerView.ViewHolder {
        TextView thisTextView;
        ImageView thisI;
        View thisItemView;

        public ElliotHolder(View itemView) {
            super(itemView);
            thisItemView = itemView;
        }
    }

}

//extends BaseAdapter {
//
//        Context context;
//        ArrayList<String> data;
//public ElliotListViewActivityAdapter(Context context, ArrayList<String> data) {
//        this.data = data;
//        this.context = context;
//
//        }
//
//@Override
//public int getCount() {
//        return data.size();
//        }
//
//@Override
//public Object getItem(int position) {
//        return data.get(position);
//        }
//
//@Override
//public long getItemId(int position) {
//        return position;
//        }
//
//@Override
//public View getView(int position, View convertView, ViewGroup parent) {
//
//        ItemHolder mItemHolder;
//        View view = convertView;
//
//        if (view == null) {
//        mItemHolder = new ItemHolder();
//        view = LayoutInflater.from(context).inflate(R.layout.list_view_item_layout, null);
//
//        mItemHolder.innerTextView = (TextView)view.findViewById(R.id.m_list_view_item_text_view);
//
//        view.setTag(mItemHolder);
//        }
//        else{
//        mItemHolder = (ItemHolder) view.getTag();
//        }
//
//        mItemHolder.innerTextView.setText(data.get(position));
//
//        return view;
//        }
//
//private class ItemHolder{
//    TextView innerTextView;
//}
//}
