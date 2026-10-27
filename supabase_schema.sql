-- Jalankan di Supabase Dashboard > SQL Editor

create table if not exists public.tasks (
    id bigint generated always as identity primary key,
    title text not null,
    subject text not null,
    deadline date not null,
    time text,
    priority text not null default 'Sedang' check (priority in ('Tinggi', 'Sedang', 'Rendah')),
    is_done boolean not null default false,
    created_at timestamptz not null default now()
);

-- Wajib untuk Supabase: tanpa ini tabel tidak bisa diakses dari anon key.
alter table public.tasks enable row level security;

-- Kebijakan sementara untuk development: semua orang boleh baca/tulis.
-- Ganti dengan kebijakan berbasis auth.uid() kalau nanti sudah pakai login.
create policy "Allow all access for now"
    on public.tasks
    for all
    using (true)
    with check (true);
