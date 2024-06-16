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
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>

        <div class="post-wrap padding-large overflow-hidden">
            <div class="container">
                <div class="row">
                    <main class="post-grid">
                        <div class="row">
                            <article class="post-item">
                                <div class="post-content">
                                    <div class="post-meta mt-4">
                                        <span class="post-category">${news.dateUpload}</span> - <span class="meta-date"><a href="blog?id=${news.topic.topicId}">${news.topic.topicName}</a></span>
                                    </div>
                                    <h1 class="post-title my-4">${news.title}</h1>
                                    <div class="hero-image col-lg-12">
                                        <img src="${news.imgNews1}" alt="${news.title}" class="img-fluid">
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
                                            <div class="element-title pe-2">Share:</div>
                                            <ul class="d-flex list-unstyled">
                                                <li>
                                                    <a href="#">
                                                        <svg class="facebook">
                                                        <use xlink:href="#facebook"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <svg class="instagram">
                                                        <use xlink:href="#instagram"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <svg class="twitter">
                                                        <use xlink:href="#twitter"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <svg class="linkedin">
                                                        <use xlink:href="#linkedin"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <svg class="youtube">
                                                        <use xlink:href="#youtube"></use>
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
                            <section id="post-comment" class="padding-medium pb-0">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="comments-wrap">
                                                <h3 class="mb-3">${comments.size()} Comments</h3>
                                                <div class="comment-list padding-small">
                                                    <c:forEach var="comment" items="${comments}">
                                                        <article class="comment-item d-flex flex-wrap mb-4">
                                                            <div class="col-lg-1 col-sm-3 me-4 mb-3">
                                                                <img src="${comment.avatarUrl}" alt="${comment.author}" class="img-fluid rounded-3">
                                                            </div>
                                                            <div class="col-lg-10 col-sm-9 author-wrap">
                                                                <div class="author-post">
                                                                    <div class="comment-meta d-flex">
                                                                        <div class="author-name fw-medium pe-1">${comment.author}</div>
                                                                        <span class="meta-date">${comment.date}</span>
                                                                    </div>
                                                                    <p class="no-margin">${comment.content}</p>
                                                                    <div class="comments-reply">
                                                                        <a href="#" class="text-primary">Reply Now</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </article>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="comment-respond mt-5">
                                                <h3>Leave a Comment</h3>
                                                <p>Your email address will not be published. Required fields are marked *</p>
                                                <form id="form" class="d-flex gap-3 flex-wrap" action="submitComment" method="post">
                                                    <div class="w-100 d-flex gap-3">
                                                        <div class="w-50">
                                                            <input type="text" name="name" placeholder="Write your name here *" class="form-control w-100" required>
                                                        </div>
                                                        <div class="w-50">
                                                            <input type="email" name="email" placeholder="Write your email here *" class="form-control w-100" required>
                                                        </div>
                                                    </div>
                                                    <div class="w-100">
                                                        <textarea name="content" placeholder="Write your comment here *" class="form-control w-100" required></textarea>
                                                    </div>
                                                    <label class="w-100">
                                                        <input type="checkbox" required class="d-inline">
                                                        <span>Save my name, email, and website in this browser for the next time.</span>
                                                    </label>
                                                    <button type="submit" name="submit" class="btn my-3">Post Comment</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </main>
                </div>
            </div>
        </div>

        <jsp:include page="../common/footer.jsp"></jsp:include>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
