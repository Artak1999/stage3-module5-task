export const getAllNews = async () => {
    const response = await fetch('news/getAll');
    if (!response.ok) throw new Error('Failed to fetch all news');
    return response.json();
};

export const getAuthors = async () => {
    const response = await fetch('news/authors');
    if (!response.ok) throw new Error('Failed to fetch authors');
    return response.json();
};

export const getNewsCount = async () => {
    const response = await fetch('news/count');
    if (!response.ok) throw new Error('Failed to fetch news count');
    return response.text();
};