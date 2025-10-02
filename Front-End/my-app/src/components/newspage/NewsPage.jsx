import React, {useEffect, useState} from "react";
import "./newspage.css"
import {Container} from "react-bootstrap";
import AddNews from "../popup/AddNews";
import DeleteNews from "../popup/DeleteNews";
import EditNews from "../popup/EditNews";
import Pagination from "../pagination/Pagination";
import SelectLimit from "../pagination/SelectLimit";
import Header from "../header/Header";
import Footer from "../footer/Footer";

const NewsPage = () => {

    const [allNews, setAllNews] = useState([]);
    const [author, setAuthor] = useState([]);
    const [count, setCount] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const [contentPerPage, setContentPerPage] = useState(3);
    const [search, setSearch] = useState("");
    const indexOfLastPost = currentPage * contentPerPage;
    const indexOfFirstPost = indexOfLastPost - contentPerPage;
    let totalPage = Math.ceil(allNews.length / contentPerPage);

    useEffect(() => {
        const fetchNews = async () => {
            try {
                fetch('news/getAll')
                    .then((response) => response.json())
                    .then((data) => {
                        setAllNews(data);
                    })
            } catch (error) {
                console.error('Error fetching all news:', error);
            }
        }
        fetchNews().catch(console.error);
    }, []);

    useEffect(() => {
        const fetchAuthors = async () => {
            try {
                fetch('news/authors')
                    .then((response) => response.json())
                    .then((data) => {
                        setAuthor(data);
                    })
            } catch (error) {
                console.error('Error fetching all news:', error);
            }
        }
        fetchAuthors().catch(console.error);
    }, []);

    useEffect(() => {
        const fetchCount = async () => {
            try {
                fetch('news/count')
                    .then((response) => response.text())
                    .then((responseText) => {
                        setCount(responseText);
                    })
            } catch (error) {
                console.error('Error fetching count of news:', error);
            }
        }
        fetchCount().catch(console.error);
    }, []);

    function handlePageChange(value){
        if(value === "&laquo;" || value === "... ") {
            setCurrentPage(1);
        }
        else if(value === "&lsaquo;"){
            if(currentPage !== 1) {
                setCurrentPage(currentPage - 1);
            }
        }
        else if(value === "&rsaquo;"){
            if(currentPage !== totalPage) {
                setCurrentPage(currentPage + 1);
            }
        }
        else if(value === "&raquo;" || value === " ...") {
            setCurrentPage(totalPage);
        }
        else {
            setCurrentPage(value);
        }
    }

    return (
        <Container>

            <Header />

            <center>
                <div style={{width: "820px", height: "600px"}}>
                    <input
                        type="search"
                        className="search"
                        onChange={e => setSearch(e.target.value)}
                        placeholder="Search News...">
                    </input>
                    <AddNews />
                    <div>
                        <h3 className="news-count">{count} News</h3>
                        <select className="select">
                            <option value="dateCreated">Date Created</option>
                        </select>
                    </div>
                    {allNews
                        .filter(item => item.title.startsWith(search) || item.content.startsWith(search))
                        .slice(indexOfFirstPost, indexOfLastPost)
                        .map((item) => (
                        <div className="box" key={item.id}>
                            <center>
                                <br/><h4><b>{item.title}</b></h4>
                                <h6 className="create-date">{item.createDate}</h6>
                                <div className="box-inner">
                                    <h6 className="content">{item.content}</h6>
                                    <h6 className="author">AUTHOR</h6>
                                    {author.filter(i => i.id === item.id).map((author) =>
                                        <h6 key={author.id} className="author-name">{author.name}</h6>
                                    )}
                                    <h6 className="tags">TAGS</h6>
                                    <div className="tags-sport">SPORT</div>
                                    <div className="tags-tennis">TENNIS</div>
                                    <DeleteNews id={item.id}/>
                                    <EditNews id={item.id}/>
                                </div>
                            </center>
                        </div>
                    ))}
                    <div style={{marginTop: "400px", marginRight: "220px"}}>
                        <Pagination
                            totalPage={totalPage}
                            page={currentPage}
                            limit={contentPerPage}
                            siblings={1}
                            onPageChange={handlePageChange}
                        />
                        <SelectLimit onLimitChange={setContentPerPage}/>
                    </div>
                </div>
            </center>

            <Footer />

        </Container>
    );
};

export default NewsPage;