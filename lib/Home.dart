import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/* 
PART - 1
---------
  If the user clicks on 'Get Details' button,
  it will interact with the flutter plugin which you will create for the iOS/Android.

curl -X 'GET' \
  'https://user-service.solobeta.co/v1/user/context' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer $access_token'

  The plugin will do a GET api call to specified API in cUrl above with the access_token
  that is passed to the flutter plugin and will return flutter parsable object.
*/

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform =
      MethodChannel('com.example.solo_flutter_example/getUserDetails');
  String accessToken =
      "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1PRkRUdTZ6VjBXRlFjVUY0YkxwRCJ9.eyJodHRwczovL3NvbG8uY28vdjEvdXNlcl9pZCI6MTAwMDAwMDEzNSwiaHR0cHM6Ly9zb2xvLmNvL3YxL3N0cmlwZV9hY2NvdW50X2lkIjoiYWNjdF8xTEQyMWU0RlB6RklhRUtYIiwiaXNzIjoiaHR0cHM6Ly9iZXRhLXNvbG8tcGF5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJzbXN8NjJiMTdiNTk2ZjFhMWE1Y2JiM2VmMmZmIiwiYXVkIjpbImh0dHBzOi8vc29sby5jby92MS8iLCJodHRwczovL2JldGEtc29sby1wYXkudXMuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY1NzEwMjc5MCwiZXhwIjoxNjU3MTg5MTkwLCJhenAiOiJyYllJSnZWN3hDQWxmUnV4dnhRVlNaaVM3a25UbFZ2aSIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwifQ.J1uhEwuJ5ulpQ0HXBHE-zSiMvAa5t9n6zYirSc0-LRt3rNe9ZhoLPO4dlLd6mHvAaF1nBtM-RTegmoTRGhuc1-N0M27-vWEahdlBd9ZMqDjOTVQruyglJIaz4wYdXecU7vkx4CjhZ4vmAqkDwyFYpqZX_we1Vs0dBClLlsVtsCjNMMoAczYMHj7DQrT-bF6K0eZUdFrQDOhN1X8cowfY5MoOQqMTZEYplGXhWarELTCclUs1oFMfLmSRe5pyv11VnT4fqNqn7GrAMBx9cmeWJZtI1h7gzHHo5WDhC8pECx6Go0tGxTY7TujxksYIS2vhoEVjebYsAoyfiwBaFxPS0g";
  String _firstName = '';

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Solo Example"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'First Name received: $_firstName',
            ),
            const SizedBox(
              height: 24,
            ),
            MaterialButton(
                child: Container(
                  color: const Color(0xFF232323),
                  padding:
                      const EdgeInsets.symmetric(horizontal: 32, vertical: 16),
                  child: const Text(
                    "Get Details",
                    style: TextStyle(color: Color(0xFFFFFFFF)),
                  ),
                ),
                onPressed: () async {
                  print(accessToken);
                  dynamic result = await platform.invokeMethod(
                      'getUserDetails', {'access_token': accessToken});
                  setState(() {
                    _firstName = result;
                  });
                }),
          ],
        ),
      ),
    );
  }
}
