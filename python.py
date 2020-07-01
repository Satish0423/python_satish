import argparse
from remote_login import RemoteLogin


parser = argparse.ArgumentParser(description='Remote login to processor')
parser.add_argument("key", type=str, help="give the ssh key")
parser.add_argument("ip", type=str, help="Enter ip address")
parser.add_argument("operation", choices=["r", "u", "d"], help="kind of operation")
args = parser.parse_args()
print(args)

if args.operation == "r":
    remote_login = RemoteLogin(args.ip, args.port, args.user, args.password, args.key,
                               "/home/user/remote_login/download_files/", "/var/log")
    command = str(input("Enter the command:"))
    remote_login.execute_commands([command])

if args.operation == "u":
    remote_path = str(input("Enter Remote Path:"))
    remote_login = RemoteLogin(args.ip, args.port, args.user, args.password, args.key,
                               "/home/user/remote_login/download_files/", remote_path)
    remote_login.upload_single_file(str(input("Give uploaded File with Path:")))

if args.operation == "d":
    local_path = str(input("Enter Local Path:"))
    remote_login = RemoteLogin(args.ip, args.port, args.user, args.password, args.key,
                               local_path, "/var/log")
    remote_login.download_file(str(input("Give File downloaded file with Path:"))) 
