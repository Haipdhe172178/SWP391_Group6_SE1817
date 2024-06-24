<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>${news.title}</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
        <style>
            .post-content {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }
            .post-meta, .post-title {
                margin-bottom: 15px;
            }
            .post-title {
                font-size: 2rem;
                font-weight: 700;
                color: #333;
            }
            .post-description p {
                line-height: 1.6;
                font-size: 1.1rem;
            }
            .post-bottom-link {
                margin-top: 20px;
                border-top: 1px solid #eee;
                padding-top: 15px;
            }
            .block-tag a {
                background: #f5f5f5;
                padding: 5px 10px;
                border-radius: 5px;
                text-decoration: none;
                color: #333;
                transition: background 0.3s;
            }
            .block-tag a:hover {
                background: #ddd;
            }
            .social-links a svg {
                fill: #555;
                width: 24px;
                height: 24px;
                transition: fill 0.3s;
            }
            .social-links a:hover svg {
                fill: #000;
            }
            .post-navigation a {
                text-decoration: none;
                color: #007bff;
                transition: color 0.3s;
            }
            .post-navigation a:hover {
                color: #0056b3;
            }
            .post-navigation svg {
                width: 24px;
                height: 24px;
                fill: #007bff;
                transition: fill 0.3s;
            }
            .post-navigation a:hover svg {
                fill: #0056b3;
            }
            .author-note {
                color: #28a745; /* Màu xanh lá cây */
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>

            <div class="post-wrap padding-large overflow-hidden">
                <div class="container">
                    <div class="row">
                        <main class="post-grid col-12">
                            <article class="post-item">
                                <div class="post-content">
                                    <div class="post-meta mt-4 text-center">
                                        <span class="post-category">${news.dateUpload}</span> - <span class="meta-date"><a href="blog?id=${news.topic.topicId}">${news.topic.topicName}</a></span>
                                </div>
                                <h1 class="post-title my-4 text-center">${news.title}</h1>
                                <div class="hero-image col-lg-12 mb-4 text-center">
                                    <div class="mb-3">
                                        <img src="${news.imgNews1}" alt="${news.title}" class="img-fluid rounded d-block mx-auto">
                                    </div>
                                    <div>
                                        <img src="${news.imgNews2}" alt="${news.title}" class="img-fluid rounded d-block mx-auto">
                                    </div>
                                </div>
                                <div class="text-center mb-4">
                                    <small class="text-muted author-note">Bài đăng được up bởi ShopBook88</small>
                                </div>
                                <div class="post-description review-item mt-4">
                                    <p>${news.content}</p>
                                </div>
                                <div class="post-bottom-link d-flex justify-content-between mt-2">
                                    <div class="block-tag">
                                        <ul class="list-unstyled d-flex">
                                            <c:if test="${not empty news.tags}">
                                                <c:forEach var="tag" items="${news.tags}">
                                                    <li class="pe-3">
                                                        <a href="blog?tag=${tag}">${tag}</a>
                                                    </li>
                                                </c:forEach>
                                            </c:if>
                                        </ul>
                                    </div>
                                    <div class="social-links d-flex">
                                        <div class="element-title pe-2">Chia sẻ:</div>
                                        <ul class="d-flex list-unstyled">

                                            <li>
                                                <a href="https://twitter.com/intent/tweet?url=${pageContext.request.requestURL}&text=${news.title}" target="_blank">
                                                    <svg class="twitter">
                                                    <use xlink:href="#twitter"></use>
                                                    </svg>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="https://www.linkedin.com/sharing/share-offsite/?url=${pageContext.request.requestURL}" target="_blank">
                                                    <svg class="linkedin">
                                                    <use xlink:href="#linkedin"></use>
                                                    </svg>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div id="single-post-navigation" class="mt-3">
                                    <div class="post-navigation d-flex flex-wrap align-items-center justify-content-between">
                                        <a itemprop="url" class="post-prev d-flex align-items-center" href="post?id=${previousPostId}">
                                            <svg class="alt-arrow-left-bold" width="44" height="44">
                                            <use xlink:href="#alt-arrow-left-bold"></use>
                                            </svg>
                                            <h4 class="card-title text-capitalize text-dark">${previousPostTitle}</h4>
                                        </a>
                                        <a itemprop="url" class="post-next d-flex align-items-center" href="post?id=${nextPostId}">
                                            <h4 class="card-title text-capitalize text-dark">${nextPostTitle}</h4>
                                            <svg class="alt-arrow-right-bold" width="44" height="44">
                                            <use xlink:href="#alt-arrow-right-bold"></use>
                                            </svg>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </main>
                </div>
            </div>
        </div>

        <jsp:include page="../common/footer.jsp"></jsp:include>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
            <script>
                function sharePost(event) {
                    event.preventDefault();
                    if (navigator.share) {
                        navigator.share({
                            title: '${news.title}',
                            text: '${news.title}',
                            url: window.location.href
                        }).then(() => {
                            console.log('Cảm ơn bạn đã chia sẻ!');
                        }).catch(err => {
                            console.error('Không thể chia sẻ bài đăng', err);
                        });
                    } else {
                        alert('Trình duyệt này không hỗ trợ chia sẻ. Vui lòng sử dụng các nút chia sẻ.');
                    }
                }
        </script>
    </body>
</html>
