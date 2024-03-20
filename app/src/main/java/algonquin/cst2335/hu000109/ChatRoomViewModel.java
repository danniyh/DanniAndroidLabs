package algonquin.cst2335.hu000109;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {
    public MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData<>();
}
/**
 * 定义了一个名为 ChatRoomViewModel 的类，它继承自 ViewModel 类，
 * 这是 Android 架构组件之一，用于存储和管理与 UI 相关的数据，并且是生命周期感知的，
 * 即它能够在配置更改（例如屏幕旋转）发生时保持数据状态。
 *
 * 在 ChatRoomViewModel 中，声明了一个 MutableLiveData 对象，
 * 泛型参数为 ArrayList<ChatMessage>。这表明 messages 这个 MutableLiveData 对象将存储 ChatMessage 对象的列表。
 * ChatMessage 可能是一个自定义的类，
 * 用于表示聊天消息的数据模型，包含消息内容、时间戳、发送者标识等信息。
 */