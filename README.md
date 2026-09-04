# WaiterApp Backend

REST API + real-time server cho ứng dụng quản lý nhà hàng WaiterApp, xây dựng bằng Kotlin và Spring Boot.

![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?logo=kotlin)
![Backend](https://img.shields.io/badge/Backend-Spring%20Boot-brightgreen?logo=springboot)
![Architecture](https://img.shields.io/badge/Architecture-MVC-blue)

App Android: [WaiterApp](https://github.com/dinh11032002/WaiterApp_Backend)

## Giới thiệu

Backend cung cấp API cho nhân viên phục vụ: đăng nhập, quản lý bàn, thực đơn và giỏ hàng, đồng bộ dữ liệu real-time giữa nhiều thiết bị cùng phục vụ một bàn.

## Công nghệ sử dụng

- **Ngôn ngữ:** Kotlin
- **Framework:** Spring Boot
- **ORM:** Spring Data JPA / Hibernate
- **Database:** MySQL
- **Real-time:** WebSocket (STOMP)
- **API Docs:** Swagger / OpenAPI
- **Testing:** JUnit 5, Mockito
- **Build tool:** Gradle

## Kiến trúc

```
Controller → Service → Repository → Database
```

- **Controller:** nhận HTTP request, trả response, không chứa business logic
- **Service:** xử lý logic nghiệp vụ, chuyển đổi Entity ↔ DTO
- **Repository:** giao tiếp trực tiếp với Database (Spring Data JPA)
- **DTO tách biệt Entity:** dữ liệu trả về client không lộ trực tiếp cấu trúc Database

## Tính năng chính

| Nhóm chức năng | Mô tả |
|---|---|
| Auth | Đăng nhập, xác thực tài khoản nhân viên |
| Table | Danh sách bàn, tìm kiếm, lọc theo trạng thái, tạo bàn |
| Category / Menu Item | Danh mục và món ăn, tìm kiếm, lọc theo danh mục |
| Cart | Thêm/giảm/xóa/thay thế món trong giỏ hàng, đồng bộ real-time qua WebSocket giữa các thiết bị cùng phục vụ một bàn |

## API Endpoints (tóm tắt)

```
POST   /api/v1/auth/login

GET    /api/v1/tables
POST   /api/v1/tables

GET    /api/v1/categories
POST   /api/v1/categories

GET    /api/v1/menu-items
POST   /api/v1/menu-items

GET    /api/v1/cart
POST   /api/v1/cart/add
POST   /api/v1/cart/decrease
POST   /api/v1/cart/replace
DELETE /api/v1/cart
DELETE /api/v1/cart/clear
```

Tài liệu API đầy đủ xem tại Swagger UI sau khi chạy server: `http://localhost:8080/swagger-ui/index.html`

## Cài đặt và chạy thử

1. Clone repository:
   ```bash
   git clone https://github.com/<username>/WaiterApp-Backend.git
   ```

2. Tạo database MySQL:
   ```sql
   CREATE DATABASE waiter_app;
   ```

3. Cấu hình kết nối database trong `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/waiter_app
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. Chạy server:
   ```bash
   ./gradlew bootRun
   ```

5. Server chạy tại `http://localhost:8080`, dữ liệu mẫu được tự động seed từ `data.sql`.

## Chạy test

```bash
./gradlew test
```

## Tác giả

**Trương Đình**
- GitHub: [github.com/dinh11032002](https://github.com/dinh11032002)
