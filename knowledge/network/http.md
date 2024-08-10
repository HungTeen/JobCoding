## HTTP 优缺点
### 优点
* 结构简单：主要包含请求头和请求体两部分。
* 灵活易扩展
  * HTTP 提供了很多字段，可以根据需求进行扩展。
  * HTTP 位于 OSI 第七层，它的下层协议均可以灵活变化。
* 跨平台：不同操作系统都可以通过 HTTP 协议来进行通信。
### 缺点
* 无状态：HTTP 是无状态协议，无法保存状态。
* 明文传输：HTTP 传输的内容都是明文，不安全。

## HTTP 状态码
| 状态码 | 描述                                 |
|-----|------------------------------------|
| 1xx | 信息性状态码，表示请求已被接收，继续处理               |
| 2xx | 成功状态码，表示请求已成功被服务器接收、理解、并接受         |
| 3xx | 重定向状态码，表示需要客户端采取进一步的操作才能完成请求       |
| 4xx | 客户端错误状态码，表示客户端看起来可能发生了错误，妨碍了服务器的处理 |
| 5xx | 服务器错误状态码，表示服务器在处理请求的过程中有错误或者异常状态发生 |
* 1xx 信息性状态码（一般用不到）
  - 100 Continue：客户端应继续其请求（预热）。
  - 101 Switching Protocols：服务器已经理解了客户端的请求，并将通过 Upgrade 消息头通知客户端采用不同的协议来完成这个请求。（如升级到 WebSocket）
* 2xx 成功状态码
  - 200 OK：请求成功。
  - 201 Created：请求已经被实现，并且有一个新的资源已经依据请求的需要而建立。
  - 204 No Content：服务器成功处理了请求，但不需要返回任何实体内容。
  - 206 Partial Content：服务器已经成功处理了部分 GET 请求。
* 3xx 重定向状态码
  - 301 Moved Permanently：永久重定向，请求的资源已被分配了新的 URL（通过 Location 字段指定）。
  - 302 Found：临时重定向，请求的资源已被分配了新的 URL（通过 Location 字段指定）。
  - 304 Not Modified：资源未被修改，可以直接使用缓存。
* 4xx 客户端错误状态码
  - 400 Bad Request：请求报文存在语法错误。
  - 401 Unauthorized：请求需要用户验证。
  - 403 Forbidden：服务器禁止访问资源。
  - 404 Not Found：服务器无法找到请求的资源。
* 5xx 服务器错误状态码
  - 500 Internal Server Error：服务器内部错误。
  - 501 Not Implemented：服务器不支持实现请求所需要的功能。
  - 502 Bad Gateway：网关错误。
  - 503 Service Unavailable：服务不可用。
  - 504 Gateway Timeout：网关超时。

## HTTP 常见字段
* Host：请求的域名，可以将请求发往同一个服务器的不同域名。
* Accept：客户端能够接收的内容类型，如 text/html、application/json、image/png 等。
* Content-Type：HTTP Body 的格式，如 application/x-www-form-urlencoded、application/json、multipart/form-data 等。
* Content-Length：HTTP Body 的长度，单位是字节。明确 HTTP Body 的边界，防止粘包问题。
* Accept-Encoding：客户端能够接收的内容编码方式，如 gzip、deflate、br 等。
* Content-Encoding：HTTP Body 的编码方式，如 gzip、deflate、br 等。
* Connection：是否采用 HTTP 长连接，取值有 Keep-Alive 和 close。
* Upgrade：客户端要求升级协议，如 WebSocket。

## HTTP 缓存
### 强制缓存
强制缓存的主动权在浏览器这边，当浏览器发现请求的缓存没有过期，则会直接使用缓存（响应码为200，标识使用了缓存），不会发起请求。
* Expires：绝对时间，表示资源的过期时间。
* Cache-Control（HTTP/1.1)：相对时间，表示资源的最大缓存时间，设置更加精细，具有更高的优先级。
### 协商缓存
协商缓存主要是指浏览器和服务器双方协商是否走缓存，如果浏览器认为缓存过期，会向服务器发起请求，由服务器决定是否使用缓存（使用缓存返回 304，否则返回200）。
* 根据最后修改时间判断是否使用缓存：
  - Last-Modified：资源最后修改时间，由服务器返回。
  - If-Modified-Since：当浏览器判断本地缓存过期时，会将这个字段的值设置为 Last-Modified 的值，发送给服务器。
  - 服务器收到请求后，将 If-Modified-Since 和资源的最后修改时间进行比较。
* 根据 ETag （资源唯一标识）来进行判断：
  - ETag：资源的唯一标识，由服务器返回。
  - If-None-Match：当浏览器判断本地缓存过期时，会将这个字段的值设置为 ETag 的值，发送给服务器。
  - 服务器收到请求后，将 If-None-Match 和资源的 ETag 进行比较。

使用 ETag 的优先级高于 Last-Modified，如果两者都存在，优先使用 ETag。
* Last-Modified 的时间精度为秒级，不能准确解决秒级之内缓存失效的问题。
* 可能缓存本身没改变，但是 Last-Modified 时间变了，导致缓存失效。
* 有些服务器不能获取文件的最后修改时间，只能通过 ETag 来判断。

<img src="/knowledge/assets/network/http-cache.png" width="800">

## HTTP 报文
### HTTP 请求报文
HTTP 请求报文由请求行、请求头部、请求体三部分组成。
- 请求行：包含请求方法、请求 URL、HTTP 协议版本
- 请求头部：包含请求的一些附加信息，如 User-Agent、Host、Accept、Connection 等
- 请求体：包含请求的数据，如 POST 请求的表单数据
#### GET 请求报文
```http request
GET /index.html HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
Upgrade-Insecure-Requests: 1
```
#### POST 请求报文
````http request
POST /submit-form HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Content-Length: 27
Accept: */*
Connection: keep-alive

name=JohnDoe&age=25
````
### HTTP 响应报文
HTTP 响应报文由状态行、响应头部、响应体三部分组成。
- 状态行：包含 HTTP 协议版本、状态码、状态消息
- 响应头部：包含响应的一些附加信息，如 Server、Content-Type、Content-Length、Last-Modified 等
- 响应体：包含响应的数据，如 HTML 文档、图片、视频等

```http request
HTTP/1.1 200 OK
Date: Mon, 23 May 2005 22:38:34 GMT
Content-Type: text/html; charset=UTF-8
Content-Length: 138
Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)
ETag: "3f80f-1b6-3e1cb03b"
Accept-Ranges: bytes
Connection: close

<html>
<head>
  <title>An Example Page</title>
</head>
<body>
  <p>Hello World, this is a very simple HTML document.</p>
</body>
</html>
```