import { Avatar, AvatarFallback } from "@/components/ui/avatar";

function UserList() {
  return (
    <>
      <div className="space-y-2">
        <div className="border rounded-md">
          <p className="py-2 px-3">{"Abhishek" || "Unassigned"}</p>
        </div>
        {[1, 1, 1, 1].map((item) => (
          <div
            key={item}
            className="flex py-2 group hover:bg-slate-800 cursor-pointer items-center space-x-4 rounded-md border px-4"
          >
            <Avatar>
              <AvatarFallback>Z</AvatarFallback>
            </Avatar>
            <div className="space-y-1">
              <p className="text-sm leading-none">Code with Zosh</p>
              <p className="text-sm text-muted-foreground">@codewithzosh</p>
            </div>
          </div>
        ))}
      </div>
    </>
  );
}

export default UserList;
