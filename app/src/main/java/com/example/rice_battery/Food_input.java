package com.example.rice_battery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Food_input extends AppCompatActivity {

    private EditText selected_item_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ate_food);

        final ListView listview = (ListView)findViewById(R.id.listView);
        selected_item_edit=(EditText) findViewById(R.id.search_edt);

        Button complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Show_nutrient.class);
                startActivity(intent);
            }
        });

        //데이터를 저장하게 되는 리스트
        List<String> list = new ArrayList<>();

        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        listview.setAdapter(adapter);


        //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);

                //텍스트뷰에 출력
                selected_item_edit.setText(selected_item);
            }
        });


        //리스트뷰에 보여질 아이템을 추가
        list.add("김밥");
        list.add("불고기");
        list.add("떡볶이");
        list.add("갈비찜");
        list.add("치킨");
        list.add("돈가스");
        list.add("부추전");
        list.add("피자");
        list.add("김치전");
        list.add("김치찌개");
        list.add("된장찌개");

        selected_item_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable edit) {
                String filterText=edit.toString();
                //검색어칠때 팝업뜸
                /*if(filterText.length()>0){
                    listview.setFilterText(filterText);
                }
                else{
                    listview.clearTextFilter();
                }*/
                //팝업 안뜨게하려면
                ((ArrayAdapter<String>)listview.getAdapter()).getFilter().filter(filterText);

            }
        });
    }
}
