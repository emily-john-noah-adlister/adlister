<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Friender</a>
            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAVoSURBVGhD7dlXiCxFFIDhMeeAOQtGzAFEQVGMqBgRfFAxYEAxYFYUzAkDBsx6xfygoj6ICQOioiJmRTEHBFHBnDD+/4xnacrTPd277ixc9sAHOzPV29PVVadO9fSmYyaJWXAwXsJv+AEPYnO0iUVwCT7GX/gcV2NZjCxmw134O/EHDkFTLIcPkR3/BdbGSOJIZF8ieDFroi6eRHZceB121qTHe8i+QNVVyGIdZO1L22FSYwFkJy49hyz2Rda+dBImNeZBduLSM8hiL2TtS8dj0uM1ZCevughZrIysfWlTTHrsg+zk4SesiLq4G9lx4WmY3kcS9nj2JX7GTmiKhfAisuPfgel5pLE97sP7eBPXYhW0iTlxFOz9j/A8TsH8mLJYEOP9AnPAVX4kQ8lMNffgz7HwC9yEbxDD4jNcgRXQFLPjIHgXfofH/opnsS3KsKPGvUAujPPxCeKLvotTEb2/I6yz4vNg3bUnslgKXkB5jByeS8BYDbfiW/jZn3BtMn23vnuWFxZz5YmCJ4wJ6YVlbSxTdkA17IA3kLX3jqwOYw+YNLJ2ugfOscZw5XbyZv+g6lU4RBxidRdtRTsfIi5E1k4Xw9gQ2V0uXY7GOBnZgZkDYZyD7HNFG+fYd8jaaC0YjyL7vOQdXxW18QqyAzMPw3CCVt93At+GLVFNEs6P/fEyqu2/grEonAvVz5o01mM/Ijso4xA0HNvxnvuMjRFhtlkaJo+IWXEM7FWPsfOMjRD/pw2zZm18jeygjKuwsS587UVEErCmuhnV4fQBHLpxl8xs7g7f7r/q9dZDtG3jGtTG48gOytwBY1fYu3EntsH3yI6R5cniMJzkpmvvnOtVlxHhVrs22pbZ2hrGjbhz8GdvJUTub/IIDIeci2oshDcga1/yGCuC2nD8PoTs4CoXK8Oe9Yt7FwyHU9Y+Y61m2BH3Dv7sLQkrhKx91QEYGi5cLjrZP5AnjgXJO+F7c8FOaHM3gr1v7Adf79x/NUirLrple7lQHoZO4dCZAWsgd3xWt9WMdDb851/2Xw16szxxkydgeB5fO1diQ+Vi67bYDn0Bzl3Xq2F1XG2Y212sVE2h1juHwkn9i28QbffzwSFs7AJfW0ut4Rv/hudYHq72zr2ycB0afuEzYVqsnthUac4/GlE4uhN8C/FgzcKyekyTs2AcCzdqUeH6XMvh652utrce8+J3w9DC0dxeLc3rWF9tAcO1Ix7fnICsfcl6yrXGiB2lF3IBYqFs4oZsGaRhz9jr2YEZv8zuMJzohre/rkyvKssL58T9yNrWsSj9z47UvUWbnij5oKF8xGl+tw7L2nvxJ6IcGnXPAIZxWI/tTk2d7puzhm08hSwcbmY6s9MDcN7FcKqGHTGeTgynox9WpFmDLjZBFhaM68PNmlvWLLosohnrOW9G57GZOQIRZrBLUd0myxLd+WParu7w6naNXTg1Gre1bdgRkTp9Ut+0TQ2mae+U4YQtU21XPkpqtbWs45P5WKj84SZrU8dKdysYru5dMmbJpzfpB23FAwYf72SfD+PuMB6v3o6sTRv+2pV+0Ebs7Ey3bRbROv76ZZgQss/bmNCFHAej7pFQWw6pSMv+Jpm1GaZ/IeOd7BvAqHsg3UV0StMjoyZ2Zj/XW+N8iqxRnVgXJpIsQmzU/CE1+zzj0xoX470RJdJYWABaXVr8nYsr4SR0f34dLCWsfmOSu0PMTtLVYzAWg/uQM+BwsQp2DrkvMiuehsPhvmVe/G/hRif7Yl1ZzU5pTF9IYcovxHE60fJCt2DKw6x3HrqmcbOODzasCob+TDDq8KJ88mhmM/NcBsv06+EaYXFn9bsZqj85TMdMFr3eP+sQwZ4y32K7AAAAAElFTkSuQmCC">
        </div>
        <ul class="nav nav-pills">
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/ads">Ads</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="/ads">Ads</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ads/create">Create Ad</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li>
                    <form action="/ads/search" method="GET">
                        <div class="form-group">
                            <input type="text" name="title" id="search" placeholder="Search for Ad">
                        </div>
                    </form>
                </li>
            </c:if>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>