package jp.deko.mock;

//import jp.deko.mock.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;;

public class DekoSearchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        // �A�C�e����ǉ����܂�
        adapter.add("�J�e�S�����猟��");
        adapter.add("�f�R����ނ��猟��");
        adapter.add("�A�i�^�ɃI�X�X���̃f�R�����猟��");
        adapter.add("�����L���O���猟��");
        adapter.add("�}�㏸�f�R�����猟��");
        adapter.add("���W���猟��");
        adapter.add("�G�߁E�C�x���g���猟��");
        ListView listView = (ListView) findViewById(R.id.listView);
        // �A�_�v�^�[��ݒ肵�܂�
        listView.setAdapter(adapter);
        // ���X�g�r���[�̃A�C�e�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // �N���b�N���ꂽ�A�C�e�����擾���܂�
                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(DekoSearchActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });
        // ���X�g�r���[�̃A�C�e�����I�����ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // �I�����ꂽ�A�C�e�����擾���܂�
                String item = (String) listView.getSelectedItem();
                Toast.makeText(DekoSearchActivity.this, item, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}