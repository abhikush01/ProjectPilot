import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { ScrollArea } from "@/components/ui/scroll-area";
import {
  fetchChatByProjectID,
  fetchChatMessages,
  sendMessage,
} from "@/Redux/Chat/Action";
import { PaperPlaneIcon } from "@radix-ui/react-icons";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";

function ChatBox() {
  const { id } = useParams();
  const [message, setMessage] = useState("");
  const dispatch = useDispatch();
  const { auth, chat } = useSelector((store) => store);

  useEffect(() => {
    dispatch(fetchChatByProjectID(id));
  }, [dispatch, id]);

  useEffect(() => {
    if (chat?.chat?.id) {
      const interval = setInterval(() => {
        dispatch(fetchChatMessages(chat.chat.id));
      }, 5000);

      return () => clearInterval(interval);
    }
  }, [dispatch, chat?.chat?.id]);

  const handleMessageChange = (e) => {
    setMessage(e.target.value);
  };

  const handleSendMessage = () => {
    dispatch(
      sendMessage({
        senderId: auth.user?.id,
        projectId: id,
        content: message,
      })
    );
    setMessage("");
  };

  return (
    <div className="sticky">
      <div className="border rounded-lg">
        <h1 className="border-b p-5"> Chat Box</h1>
        <ScrollArea className="h-[32rem] w-full p-5 flex gap-3 flex-col">
          {chat.messages?.map((item) =>
            item?.sender?.id !== auth.user?.id ? (
              <div className="flex gap-2 mb-2 justify-start" key={item?.id}>
                <Avatar>
                  <AvatarFallback>{item?.sender?.fullname[0]}</AvatarFallback>
                </Avatar>
                <div className="space-y-2 py-2 px-5 border rounded-ss-2xl rounded-e-xl">
                  <p>{item?.sender?.fullname}</p>
                  <p className="text-gray-300">{item?.content}</p>
                </div>
              </div>
            ) : (
              <div className="flex gap-2 mb-2 justify-end" key={item?.id}>
                <div className="space-y-2 py-2 px-5 border rounded-se-2xl rounded-s-xl">
                  <p>{item?.sender.fullname}</p>
                  <p className="text-gray-300">{item?.content}</p>
                </div>
                <Avatar>
                  <AvatarFallback>{item?.sender.fullname[0]}</AvatarFallback>
                </Avatar>
              </div>
            )
          )}
        </ScrollArea>
        <div className="relative p-0">
          <Input
            placeHolder="type message"
            className="py-7 border-t outline-none focus:outline-none focus:right-0 rounded-none border-b-0 border-x-0"
            value={message}
            onChange={handleMessageChange}
          />
          <Button
            onClick={handleSendMessage}
            className="absolute right-2 top-3 rounded-full"
            size="icon"
            variant="ghost"
          >
            <PaperPlaneIcon />
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ChatBox;
