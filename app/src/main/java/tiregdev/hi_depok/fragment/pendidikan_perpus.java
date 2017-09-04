package tiregdev.hi_depok.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tiregdev.hi_depok.R;
import tiregdev.hi_depok.adapter.adapter_searchData;
import tiregdev.hi_depok.model.itemObject_searchData;

/**
 * Created by Muhammad63 on 9/1/2017.
 */

public class pendidikan_perpus extends Fragment {

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pendidikan_perpus,container, false);
        setupAdapter();
        return v;
    }

    public void setupAdapter(){
        List<itemObject_searchData> rowListItem = getAllItemList();
        LinearLayoutManager lLayout = new LinearLayoutManager(getContext());

        RecyclerView rView = (RecyclerView)v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        adapter_searchData rcAdapter = new adapter_searchData(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<itemObject_searchData> getAllItemList(){
        List<itemObject_searchData> allItems = new ArrayList<>();
        allItems.add(new itemObject_searchData("Perpus 1","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 2","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 3","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 4","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 1","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 2","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 3","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));
        allItems.add(new itemObject_searchData("Perpus 4","Jalan Raya Muchtar No. 22, Sawangan Baru, Sawangan, Depok, Jawa Barat,6511",
                getResources().getString(R.string.jarak), R.drawable.massege_icon));

        return allItems;
    }

}
